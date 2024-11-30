package Demo;
import javax.swing.*;

class Swing {
    Swing() {
        JFrame frame = new JFrame("SwingDemo");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello World");
        frame.add(label);
        frame.setVisible(true);
    }
    Swing(int a, int b) {
        JFrame frame = new JFrame("SwingDemo");
        frame.setSize(a, b);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello World");
        frame.add(label);
        frame.setVisible(true);
    }
    Swing(String i) {
        JFrame frame = new JFrame("SwingDemo");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel(i);
        frame.add(label);
        frame.setVisible(true);
    }
}
public class SwingDemo {
    public static void main(String[] args) {
        Swing swing = new Swing();
    }
}