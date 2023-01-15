package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Printer {
    public static int[][] MyImagePrinter(String path, char white, char black) {
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));

            int[][] imageSize = new int[image.getWidth()][image.getHeight()];
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int clr = image.getRGB(x, y);
                    if (clr == Color.BLACK.getRGB()) {
                        imageSize[x][y] = black;
                    } else if (clr == Color.WHITE.getRGB()) {
                        imageSize[x][y] = white;
                    }
                }
            }
            return imageSize;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
