package Demo;

import javax.swing.*;
import java.awt.*;

public class PaintBoard {

    private static void createAndShowGUI() {
        int width, height;
        JFrame frame = new JFrame("Paint Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Option option = new Option();
        width = Option.width;
        height = Option.height;
        frame.setSize(width, height);
        Board board = new Board(width, height+10); // Create a single Board instance
        MyPanel myPanel = new MyPanel(board);

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




