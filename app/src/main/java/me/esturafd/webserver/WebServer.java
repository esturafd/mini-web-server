package me.esturafd.webserver;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer extends Thread {
    
    private ServerSocket server;
    private File path;

    public WebServer(int port, String path) {
        try {
            this.server = new ServerSocket(port);
            this.path = path == null? null: new File(path);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = server.accept();
                new RequestProcessor(socket, path).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
