

package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private AnnotationConfigApplicationContext applicationContext;

    public Server(int port) {
        this.port = port;
        this.applicationContext = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
    }

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            String username = "";
            String password = "";
            String signUp = "";
            UsersServiceImpl service = applicationContext.getBean("usersServiceImpl", UsersServiceImpl.class);

            Socket clientSocket = serverSocket.accept();

            System.out.println("Hello from Server!");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());

            signUp = readWrite("Hello from server!", writer, reader);

            while (!signUp.equals("signUp")) {
                signUp = readWrite("You need to sign up", writer, reader);
            }

            username = readWrite("Enter username: ", writer, reader);
            password = readWrite("Enter password: ", writer, reader);

            System.out.println("Entered username = " + username);
            System.out.println("Entered password = " + password);

            service.signUp(username, password);

            writer.write("Successful!");
            writer.flush();

            writer.close();
            reader.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("An error occurred");
            System.exit(-1);
        }

    }

    private String readWrite(String message, OutputStreamWriter writer, BufferedReader reader) throws IOException {

        writer.write(message + "\n");
        writer.flush();

        return reader.readLine();
    }

}