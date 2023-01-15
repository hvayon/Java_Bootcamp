package ex00;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SignatureParser {

    private static final String END = "42";
    private static final String RESULT = "result.txt";


    public Map<String, String> extractSignatures(File signature) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Map<String, String> signatures = new HashMap<>();
        InputStream inputstream = new FileInputStream(signature);
        try {
            int data = inputstream.read();
            while (data != -1) {
                sb.append((char)data);
                data = inputstream.read();
            }
            inputstream.close();
        } catch (IOException e) {
            System.err.println("File read error");
        }
        for (String tmp : sb.toString().split("\n")) {
            signatures.put(tmp.split(", ")[1], tmp.split(", ")[0]);
        }
        return signatures;
    }

    public void getFiles(Map<String, String> signatures) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = new FileOutputStream(RESULT);
        StringBuilder builder = new StringBuilder();
        String input;
        int flag;

        while (!(input = scanner.nextLine()).equals(END)) {
            flag = 0;
            try {
                for (String key : signatures.keySet()) {
                    fileInputStream = new FileInputStream(input);
                    for (int i = 0; i < key.length(); i++) {
                        builder.append(String.format("%02X ",fileInputStream.read()));
                        if (key.startsWith(builder.toString())) {
                            fileOutputStream.write(signatures.get(key).getBytes());
                            fileOutputStream.write('\n');
                            flag = 1;
                            System.out.println("PROCESSED");
                            break;
                        }
                    }
                    builder.setLength(0);
                    fileInputStream.close();
                    }
                if (flag == 0) {
                    System.out.println("UNDEFINED");
                }
            } catch (IOException e) {
                if (fileInputStream != null) {
                    fileOutputStream.close();
                }
                scanner.close();
                fileOutputStream.close();
                System.err.println("Could not read file");
                break;
            }
        }
        scanner.close();
        fileOutputStream.close();
    }
}