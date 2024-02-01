package Pong.Controller2;

import Pong.Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientServerThread extends Thread {
    private ServerSocket serverSocket;
    private Socket socket;
    private PongModel model;
    private PongController controller;
    private ObjectOutputStream oos;
    public boolean isServer;
    private Object lock = new Object();

    public ClientServerThread(PongModel model, PongController controller) {
        this.model = model;
        this.controller = controller;
    }

    public boolean getIsServer() {
        return isServer;
    }

    public static ClientServerThread newServer(int port, PongModel model, PongController controller) {
        var cst = new ClientServerThread(model, controller);
        cst.isServer = true;

        try {
            cst.serverSocket = new ServerSocket(port);
            cst.start();
        } catch (IOException e) {
            System.out.println("Port " + port + " ist besetzt . Versuch ein Client zu sein ");
            cst.isServer = false;
            cst.startClient("localhost", port); // Try to connect as a client
        }

        return cst;
    }

    public static ClientServerThread newClient(String ip, int port, PongModel model, PongController controller) {
        var cst = new ClientServerThread(model, controller);
        cst.startClient(ip, port);
        return cst;
    }

    private void startClient(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            isServer = false;
        } catch (IOException e) {
            System.out.println("Wird jetzt ein server");
            startServer(port); // If connection fails, start as a server
        }
    }

    private void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            isServer = true;
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public void send(Object obj) {
        try {
            if (oos != null) {
                oos.reset();
                oos.writeObject(obj);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            // If this is a server accept one client
            if (socket == null) {
                socket = serverSocket.accept();
                oos = new ObjectOutputStream(socket.getOutputStream());
            }

            // Read objects
            var ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Object obj = ois.readObject();
                if (obj instanceof Character) {
                    char in = ((Character) obj).charValue();
                    controller.userInput(in);
                } else if (obj instanceof PongData) {
                     this.model.data = ((PongData) obj);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
