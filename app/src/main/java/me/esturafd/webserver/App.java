/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package me.esturafd.webserver;

public class App {
    public static void main(String[] args) {
        new WebServer(80, args.length > 0? args[0]: null).start();
    }
}
