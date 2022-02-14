package me.esturafd.webserver;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Response {

    private List<String> content;

    public Response(String message, String contentType, int code) {
        content = Arrays.asList(
            String.format("HTTP/1.0 %d OK \n", code),
            String.format("Date: %s \n", new Date().toString()),
            "Server: esturafd-server/1.0 \n",
            String.format("Conten-Type: %s \n", contentType),
            "Expires: Thu 01 Dec 1994 16:00:00 GMT \n",
            String.format("Content-Length: %d \n", message.length()),
            String.format("Last-modifed: %s \n\n", new Date(System.currentTimeMillis()).toString()),
            message
        );
    }

    @Override
    public String toString() {
        return String.join("", content);
    }

    public byte[] getBytes() {
        return toString().getBytes();
    }
}
