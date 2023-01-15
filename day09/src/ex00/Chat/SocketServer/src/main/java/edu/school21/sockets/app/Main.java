package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.server.Server;

@Parameters(separators = "=")
public class Main {

    @Parameter(names = {"--port", "-p"})
    int port;

    public static void main(String[] args) {

        Main main = new Main();

        JCommander.newBuilder().addObject(main).build().parse(args);

        main.run();

    }

    void run() {
        Server server = new Server(port);
        server.start();
    }

}