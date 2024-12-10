package Demo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TopLevelDemo {
    public static BufferedImage paintImage = new BufferedImage(500, 600, BufferedImage.TYPE_INT_RGB);
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("TopLevelDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        Board board = new Board(); // Create a single Board instance
        MyPanel myPanel = new MyPanel(board); // Pass it to MyPanel

        frame.add(board, BorderLayout.NORTH);
        frame.add(myPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}




