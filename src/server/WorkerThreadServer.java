package server;


import utility.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class WorkerThreadServer extends Thread {
    private ServerSocket serverSocket;
    private List<User> clients;

    private Server server;
    public WorkerThreadServer(ServerSocket serverSocket, List<User> clients, Server server) {
        this.serverSocket = serverSocket;
        this.clients = clients;
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            // accepts a new client
            try {
                Socket client = serverSocket.accept();

                // get nickname of newUser
                String nickname = (new Scanner(client.getInputStream())).nextLine();
                nickname = nickname.replace(",", ""); //  ',' use for serialisation
                nickname = nickname.replace(" ", "_");
                System.out.println("New Client: \"" + nickname + "\"\n\t     Host:" + client.getInetAddress().getHostAddress());

                // create new User
                User newUser = new User(client, nickname);

                // add newUser message to list
                this.clients.add(newUser);

                // Welcome msg
                newUser.getOutStream().println(
                        "<b>Welcome</b> " + newUser.toString()
                );

                // create a new thread for newUser incoming messages handling
                new Thread(new MessageHandlerThread(server, newUser)).start();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
