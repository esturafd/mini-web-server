package me.esturafd.webserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.stream.Collectors;

public class RequestProcessor extends Thread {
    
    private Socket socket;
    private File path;

    public RequestProcessor(Socket socket, File path) {
        this.socket = socket;
        this.path = path;
    }

    @Override
    public void run() {
        try (BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream())) {
            output.write(new Response(getContent(), "text/html", 200).getBytes());
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getContent() {
        if (path == null) return "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return String.join("\n", reader.lines().collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
