package tools;

import java.net.*;
import java.io.*;

public class Server extends Thread {
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private Coords WriteObject;
    private int port;

    // constructor with port
    public Server(int port){
        this.port = port;
    }

    @Override
    public void run() {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");


            ///////////
            //OUTPUT
            ////////////
            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            System.out.println("gggg");

            while (true){
                objectOutputStream.writeObject(WriteObject);
            }

        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }

    }



    public void setWriteObject(Coords writeObject) {
        WriteObject = writeObject;
    }

}
