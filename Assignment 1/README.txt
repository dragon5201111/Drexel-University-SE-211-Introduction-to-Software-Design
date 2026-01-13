Before running the program:
1.) Ensure JDK 24.0.1 or higher is installed. Check this with the command java -version
2.) Extract Assignment 1 zip file.

Notes:
For the client and server programs, you will be prompted to enter a port number. Press enter to use the default port. If you choose
your own port, make sure the client and server use the same port number.

To run the program follow these steps:
1.) Open two terminal windows. One for the client and server.
2.) Navigate the to the assignment directory in both terminal windows. Your current working directory NEEDS to be
something like: ""../../Assignment 1"
3.) Issue the following command in one terminal window: javac -d bin ./src/*.java
4.) To start the server, in one terminal window run: java -cp bin Server
4.5) Enter a port number.
5.) To start a client, in the other terminal window run: java -cp bin Client
5.5) Enter a port number.
6.) Start testing commands in the client terminal window.

Issues:
This assignment supports multiple clients. When multiple clients are connected, and one client issues quit command, the client and server stop but the other clients remain
until they issue a quit command.