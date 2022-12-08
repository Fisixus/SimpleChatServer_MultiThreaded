package server;

import utility.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private List<User> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Server();
    }

    private Server(){
        try{
            ServerSocket serverSocket = new ServerSocket(4000);
            System.out.println("Port 4000 is now open.");
            //this.clients = new ArrayList<User>();
            ExecutorService executor = Executors.newFixedThreadPool(999);

            for (int i = 1; i < 1000; i++) {
                WorkerThreadServer workerThread = new WorkerThreadServer(serverSocket,clients, this);
                executor.submit(workerThread);
            }
            executor.shutdown();
            while(!(executor.isTerminated())){}
            serverSocket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // delete a user from the list
    public void removeUser(User user){
        this.clients.remove(user);
    }

    // send incoming msg to all Users
    public void errorMsg(String msg, User userSender) {
        for (User client : this.clients) {
            if(client.getNickname().equals(userSender.getNickname())){
                client.getOutStream().println(
                        "<span> " + msg+"</span>");
                break;
            }
        }
    }

    // send list of clients to all Users
    public void updateAllUsersList(){
        for (User client : this.clients) {
            client.getOutStream().println(this.clients);
        }
    }

    // send message to a User (String)
    public void sendMessageToUser(String msg, User userSender, String userNickname){
        boolean find = false;
        for (User client : this.clients) {
            if (client.getNickname().equals(userNickname) && client != userSender) {
                find = true;
                userSender.getOutStream().println(userSender.toString() + " -> " + client.toString() +": " + msg);
                client.getOutStream().println(
                        "(<b>Private</b>)" + userSender.toString() + "<span>: " + msg+"</span>");
            }
        }
        if (!find) {
            userSender.getOutStream().println("(<b>NO USER EXISTS, IN THAT NICKNAME!</b>)");
        }
    }
}
