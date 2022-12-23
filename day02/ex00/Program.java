package ex00;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class Program {

    private static final String SIGNATURES = "/Users/hvayon/Desktop/Java_Bootcamp._Day02-0/src/ex00/signatures.txt";
    public static void main (String[] args) throws FileNotFoundException {

        SignatureParser parser = new SignatureParser();
        Map<String, String> map = parser.extractSignatures(new File(SIGNATURES));
        try {
            parser.getFiles(map);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}