package Demo;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;
import java.io.File;

public class Option extends JComponent implements Accessible {
    public static int width, height;
    public static BufferedImage paintImage;
    File file;
    String input;
    StringTokenizer st;

    Option() {
            input = JOptionPane.showInputDialog("Please input the width and height of the canvas.\n" +
                    "Please use SPACE to separate those two numbers:\n" +
                    "(if you input nothing, the default size of canvas is 500x600)");
            try {
                st = new StringTokenizer(input);
                width = Integer.parseInt(st.nextToken());
                height = Integer.parseInt(st.nextToken());
            }catch (Exception e) {
                width = 500;
                height = 600;
            }
            paintImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
}
