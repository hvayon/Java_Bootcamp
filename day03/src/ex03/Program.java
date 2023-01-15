package ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Program {
    private static final String INPUT_FLAG = "--threadsCount=";
    private static final String URLS_FILE = "/Users/hvayon/Desktop/Java_Bootcamp._Day03-0/src/ex03/files_urls.txt";

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith(INPUT_FLAG)) {
            System.err.println("Wrong arguments");
            System.exit(0);
        }
        int threadsNumber = Integer.parseInt(args[0].substring(INPUT_FLAG.length()));
        String currentUrl;
        LinkedList<String> urls = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(URLS_FILE));
            while ((currentUrl = reader.readLine()) != null) {
                urls.add(currentUrl);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        MyFileReader.setUrls(urls);

        for (int i = 0; i < threadsNumber; i++) {
            new Thread(new Threader(i + 1), "Thread-" + (i + 1)).start();
        }
    }
}