package Pong.Controller;

import Pong.Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Please note that this application does not handle exceptions well.
 * For example, the client crashes as soon as the server is closed.
 * In your project, you should handle these errors with more care.
 */
public class ClientServerThread extends Thread {
    private ServerSocket serversocket;
    private Socket socket;
    private PongModel model;
    private PongController controller;
    private ObjectOutputStream oos;
    public boolean isServer;
    private Object lock = new Object();

    public ClientServerThread(PongModel model,PongController controller) {
        this.model = model;
        this.controller = controller;
    }
    public boolean getIsServer(){
        return isServer;
    }


    public static ClientServerThread newServer(int port, PongModel model, PongController controller) {

        var cst = new ClientServerThread(model, controller);
        cst.isServer = true;
        try {
            cst.serversocket = new ServerSocket(port);
            cst.isServer = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        cst.start();
        return cst;
    }

    public static ClientServerThread newClient(String ip, int port, PongModel model, PongController controller) {
        var cst = new ClientServerThread(model,controller);
        try {
            cst.socket = new Socket(ip, port);
            cst.isServer = false;
            cst.oos = new ObjectOutputStream(cst.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cst.start();
        return cst;
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public void send(Object obj) {
        try {
            if(oos != null) {
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
            if(socket == null) {
                socket = serversocket.accept();
                oos = new ObjectOutputStream(socket.getOutputStream());
            }

            // Read objects
            var ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Object obj = ois.readObject();
                if(obj instanceof Character) {
                    char in = ((Character)obj).charValue();
                    controller.userInput(in);
                } else if (obj instanceof PongData) {
                    this.model.data =((PongData) obj);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
