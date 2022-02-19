package me.esturafd.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer extends Thread {
    
    private RequestStorage storage;
    private ServerSocket server;
    //private File path;

    public WebServer(int port, RequestStorage storage) {
        try {
            this.storage = storage;
            this.server = new ServerSocket(port);
            //this.path = path == null? null: new File(path);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        while (true) storage.produce(this::produce);
    }

    private Socket produce() {
        try {
            return server.accept();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
