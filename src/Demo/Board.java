package Demo;

import javax.swing.*;
import java.awt.*;

class Board extends JPanel {

    JLabel s, b, c;

    public Board(int width, int height) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.pink);
        setLayout(new FlowLayout());
        setSize(width, 10);

        s = new JLabel("Mode: Viewing");
        add(s);
        b = new JLabel("Bold Size: 2");
        add(b);

        c = new JLabel("Colour: BLACK");
        c.setForeground(Color.white);
        c.setBackground(Color.BLACK);
        c.setOpaque(true);
        add(c);

    }
}
