package tools;

import java.net.*;
import java.io.*;

public class Server extends Thread {
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream output = null;
    private BufferedReader br = null;
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

            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));


            String str="",str2="";

            // reads message from client until "Over" is sent
            while(!str.equals("stop")){
                try
                {
                    str=in.readUTF();
                    System.out.println("client says: "+str);
                    str2=br.readLine();
                    output.writeUTF(str2);
                    output.flush();
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            in.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
