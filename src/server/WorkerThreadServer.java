package server;


import utility.User;

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
                System.out.println(nickname + " has connected!");

                // create new User
                User newUser = new User(client, nickname);

                // add newUser message to list
                this.clients.add(newUser);

                // Welcome msg
                newUser.getOutStream().println(
                        "Welcome " + newUser.toString()
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
