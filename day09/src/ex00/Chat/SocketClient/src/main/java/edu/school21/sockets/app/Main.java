package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Parameters(separators = "=")
public class Main {

    @Parameter(names = {"--server-port", "-p"})
    int port;

    public static void main(String[] args) {

        Main main = new Main();

        JCommander.newBuilder().addObject(main).build().parse(args);
        main.run();

    }

    public void run() {
        try (Socket clientSocket = new Socket("localhost", port)) {
            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            Scanner scanner = new Scanner(System.in);
            String input = reader.readLine();

            while (!input.equals("Enter password: ")) {
                show(input, writer, scanner);
                input = reader.readLine();
            }

            show(input, writer, scanner);

            if (reader.readLine().startsWith("Successful!")) {
                System.out.println("Successful!");
            }

            reader.close();
            writer.close();
            scanner.close();

        } catch (IOException e) {
            System.err.println("An error has occurred!");
            System.exit(1);
        }
    }

    private void show(String message, OutputStreamWriter writer, Scanner scanner) throws IOException {

        System.out.println(message);
        System.out.print("-> ");

        writer.write(scanner.nextLine() + "\n");
        writer.flush();
    }

}