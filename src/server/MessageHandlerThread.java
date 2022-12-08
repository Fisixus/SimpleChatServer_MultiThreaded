package server;

import server.Server;
import utility.User;

import java.util.Scanner;

public class MessageHandlerThread implements Runnable {

    private Server server;
    private User user;

    public MessageHandlerThread(Server server, User user) {
        this.server = server;
        this.user = user;
        this.server.broadcastAllUsers();
    }

    @Override
    public void run() {
        String message;

        // when there is a new message, broadcast to all
        Scanner sc = new Scanner(this.user.getInputStream());
        while (sc.hasNextLine()) {
            message = sc.nextLine();

            if (message.charAt(0) == '@'){
                if(message.contains(" ")){
                    System.out.println("private msg : " + message);
                    int firstSpace = message.indexOf(" ");
                    String userPrivate= message.substring(1, firstSpace);
                    server.sendMessageToUser(
                            message.substring(
                                    firstSpace+1, message.length()
                            ), user, userPrivate
                    );
                }
            }
            else{
                // update user list
                server.broadcastMessages(message, user);
            }
        }
        // end of Thread
        server.removeUser(user);
        this.server.broadcastAllUsers();
        sc.close();
    }
}
