# SimpleChatServer_MultiThreaded

In the server part, you can see who sends a message to whom. Also, you can see the updates when someone disconnects or connects.
You can run more than one client for testing multithreading features. 
As a user, When you connect to a server, you need to specify a nickname.
After that, you can see which people are online, for sending a message to them, you need to write a message looking like "@nickname ...".
If you don't use it like that, you get a warning message from the server. By applying these steps, you can communicate with online users concurrently.
As a user, when you want to log out, you need to type "disconnect" as a message.

Some important notes: 
- Nicknames are unique identifiers! Do not create users who have the same nickname.
- Do not send a message to multiple users. Ex. @nickname1 @nickname2 hello!
- For every sentence, you need to start with @nickname for a successful message.


An example scenario in user1:

Client successfully connected to server!
Enter a nickname: user1
Welcome user1
ONLINE USERS LIST: [user1]
ONLINE USERS LIST: [user1,  user2]
(Private)user2: hey, how are you?
@user3 hey
NO USER EXISTS IN THAT NICKNAME!
user2 hey
You need to start @ for messaging to an user!
@user2 hey, I am good!
user1 -> user2: hey, I am good!
disconnect

An example scenario in user2:

Client successfully connected to server!
Enter a nickname: user2
Welcome user2
ONLINE USERS LIST: [user1,  user2]
@user1 hey, how are you?
user2 -> user1: hey, how are you?
(Private)user1: hey, I am good!
ONLINE USERS LIST: [user2]
disconnect

An example scenario in server:

Port 4000 is now open.
user1 has connected!
user2 has connected!
user2 has sent a message to user1
user1 has sent a message to user3
user1 has sent a message to user2
user1 has left!
user2 has left!
