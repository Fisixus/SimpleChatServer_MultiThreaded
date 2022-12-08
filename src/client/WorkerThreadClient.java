package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class WorkerThreadClient extends Thread{

    String host;
    int port;

    public WorkerThreadClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            // connect client to server
            Socket client = new Socket(host, port);
            System.out.println("Client successfully connected to server!");

            // Get Socket output stream (where the client send her mesg)
            PrintStream output = new PrintStream(client.getOutputStream());

            // ask for a nickname
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a nickname: ");
            String nickname = sc.nextLine();

            // send nickname to server
            output.println(nickname);

            // create a new thread for server messages handling
            new Thread(new MessageHandlerThread(client.getInputStream())).start();

            // read messages from keyboard and send to server
            System.out.println("Messages: \n");

            // while new messages
            while (sc.hasNextLine()) {
                output.println(sc.nextLine());
            }

            // end ctrl D
            output.close();
            sc.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
