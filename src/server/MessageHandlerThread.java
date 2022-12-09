package server;

import utility.User;

import java.util.Scanner;

public class MessageHandlerThread implements Runnable {

    private Server server;
    private User user;

    public MessageHandlerThread(Server server, User user) {
        this.server = server;
        this.user = user;
        this.server.updateAllUsersList();
    }

    @Override
    public void run() {
        String message;

        Scanner sc = new Scanner(this.user.getInputStream());
        while (sc.hasNextLine()) {
            message = sc.nextLine();
            if(message.equals("disconnect")){
                System.out.println(this.user + " has left!");
            }
            else if (message.charAt(0) == '@'){
                if(message.contains(" ")){
                    int firstSpace = message.indexOf(" ");
                    String userPrivate= message.substring(1, firstSpace);

                    System.out.println(this.user + " has sent a message to " + userPrivate);

                    server.sendMessageToUser(
                            message.substring(
                                    firstSpace+1, message.length()
                            ), user, userPrivate
                    );
                }
            }
            else{
                server.errorMsg("You need to start @ for messaging to an user!", user);
            }
        }
        // end of Thread
        server.removeUser(user);
        this.server.updateAllUsersList();
        sc.close();
    }
}
