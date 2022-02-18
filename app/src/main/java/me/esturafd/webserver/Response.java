package me.esturafd.webserver;

import java.util.Date;

public class Response {

    private String content;
    private static final String RESPONSE_TEMPLATE = 
        "HTTP/1.0 %d OK \n" +
        "Date: %s \n" +
        "Server: MiniWebServer(esturafd)/1.0 \n" +
        "Content-Type: %s \n" +
        "Expires: Thu 01 Dec 1994 16:00:00 GMT \n" +
        "Content-Length: %d \n" +
        "Last-Modified: %s \n\n%s";

    public Response(String message, String contentType, int code) {
        content = String.format(
            RESPONSE_TEMPLATE, 
            code,
            new Date().toString(),
            contentType,
            message.length(),
            new Date(System.currentTimeMillis()).toString(),
            message
        );
    }

    @Override
    public String toString() {
        return content;
    }

    public byte[] getBytes() {
        return toString().getBytes();
    }
}
