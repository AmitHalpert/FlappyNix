package tools;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {

    private Coords coords;
    private Socket socket;
    private int port;
    private String hostname;

    public Client(String hostname, int port){
        this.port = port;
        this.hostname = hostname;
    }


    @Override
    public void run() {
        try {
            socket = new Socket(hostname, port);
            System.out.println("Connected!");

            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


            while (true){
                coords = (Coords) objectInputStream.readObject();
            }


        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }


}
