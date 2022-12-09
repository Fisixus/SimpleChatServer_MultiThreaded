# SimpleChatServer_MultiThreaded

In the server part, you can see who sends a message to whom. Also, you can see the updates when someone disconnects or connects.<br />
You can run more than one client for testing multithreading features. <br />
As a user, When you connect to a server, you need to specify a nickname.<br />
After that, you can see which people are online, for sending a message to them, you need to write a message looking like "@nickname ...".<br />
If you don't use it like that, you get a warning message from the server. By applying these steps, you can communicate with online users concurrently.<br />
As a user, when you want to log out, you need to type "disconnect" as a message.<br />
<br />
Some important notes: <br />
- Nicknames are unique identifiers! Do not create users who have the same nickname.
- Do not send a message to multiple users. Ex. @nickname1 @nickname2 hello!
- For every sentence, you need to start with @nickname for a successful message.


An example scenario in user1:<br />
<br />
Client successfully connected to server!<br />
Enter a nickname: user1<br />
Welcome user1<br />
ONLINE USERS LIST: [user1]<br />
ONLINE USERS LIST: [user1,  user2]<br />
(Private)user2: hey, how are you?<br />
@user3 hey<br />
NO USER EXISTS IN THAT NICKNAME!<br />
user2 hey<br />
You need to start @ for messaging to an user!<br />
@user2 hey, I am good!<br />
user1 -> user2: hey, I am good!<br />
disconnect<br />
<br />
An example scenario in user2:<br />
<br />
Client successfully connected to server!<br />
Enter a nickname: user2<br />
Welcome user2<br />
ONLINE USERS LIST: [user1,  user2]<br />
@user1 hey, how are you?<br />
user2 -> user1: hey, how are you?<br />
(Private)user1: hey, I am good!<br />
ONLINE USERS LIST: [user2]<br />
disconnect<br />
<br />
An example scenario in server:<br />
<br />
Port 4000 is now open.<br />
user1 has connected!<br />
user2 has connected!<br />
user2 has sent a message to user1<br />
user1 has sent a message to user2<br />
user1 has left!<br />
user2 has left!<br />
