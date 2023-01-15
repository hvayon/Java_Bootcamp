package edu.school21.printer.app;

import edu.school21.printer.logic.Printer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Program {
    public static void main(String args[]) {
        if (args.length != 3 || args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Wrong input");
            System.exit(0);
        } else if (!Files.exists(Paths.get(args[2])) || Files.isDirectory(Paths.get(args[2])) ||
                !Files.isReadable(Paths.get(args[2]))) {
            System.err.println("Bad file");
            System.exit(0);
        }
        File file = new File(args[2]);
        char white = args[0].charAt(0);
        char black = args[1].charAt(0);
        int[][] image = Printer.MyImagePrinter(file.toString(), white, black);
        if (image == null) {
            System.err.println("Image not found");
        }
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[x].length; y++) {
                System.out.print((char) image[y][x]);
            }
            System.out.println();
        }
    }
}
