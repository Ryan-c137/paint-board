package Demo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

class MyPanel extends JPanel {

    private int x, y, bold, boldDraw, point, colourNumber;
    private char key;
    private DrawRepository drawRepository;
    private Color color;
    private String colourString;
    Graphics2D gg;

    public MyPanel(Board board) {
        x = y = -20;
        key = 'q';
        bold = 2;
        boldDraw = 2;
        point = -1;
        color = Color.BLACK;
        colourNumber = 0;
        colourString = new String();
        drawRepository = new DrawRepository();
        gg = TopLevelDemo.paintImage.createGraphics();

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setFocusable(true); // Make sure the panel can receive focus
        this.requestFocusInWindow();

        gg.setColor(Color.WHITE); // Set the background color to white
        gg.fillRect(0, 0, TopLevelDemo.paintImage.getWidth(), TopLevelDemo.paintImage.getHeight());

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                        key = 'w';
                        point++;
                        
                        // If this is a new draw, create a new place to store this draw
                        if (point == drawRepository.size()) {
                            drawRepository.create();
                        }
                        // If this is the draw after undo, means rewrite this space which point pointing to,
                        // and delete all old draws after that.
                        if (point < drawRepository.size()) {
                            drawRepository.get(point).clear();
                            for (int i = point+1; i < drawRepository.size(); i++) {
                                drawRepository.delete(i);
                            }
                        }
                        board.s.setText("Mode: Drawing");
                        break;
                    case 'q':
                        key = 'q';
                        board.s.setText("Mode: Viewing");
                        break;
                    case '=':
                        bold += 1;
                        if (bold >= 20) {
                            bold = 20;
                        }
                        board.b.setText("Bold Size: " + bold);
                        boldDraw = bold;
                        break;
                    case '-':
                        bold -= 1;
                        if (bold <= 1) bold = 1;
                        board.b.setText("Bold Size: " + bold);
                        boldDraw = bold;
                        break;
                    case '~':
                        key = '~';
                        board.s.setText("Mode: Viewing");
                        point++;
                        
                        // Empty canvas is using a new space to store as a giant white cube painted all of place
                        if (point == drawRepository.size()) {
                            drawRepository.create();
                        }
                        if (point < drawRepository.size()) {
                            drawRepository.get(point).clear();
                            for (int i = point+1; i < drawRepository.size(); i++) {
                                drawRepository.delete(i);
                            }
                        }
                        drawRepository.draw(point, 300, 300, 350, Color.WHITE);

                        gg.setColor(Color.WHITE); // Set the background color to white
                        gg.fillRect(0, 0, TopLevelDemo.paintImage.getWidth(), TopLevelDemo.paintImage.getHeight());

                        repaint();
                        break;
                    case 'u':
                        key = 'u';
                        board.s.setText("Mode: Viewing");
                        if (point == -1) {
                            key = 'q';
                            break;
                        }

                        gg.setColor(Color.WHITE); // Set the background color to white
                        gg.fillRect(0, 0, TopLevelDemo.paintImage.getWidth(), TopLevelDemo.paintImage.getHeight());

                        repaint();
                        break;
                    case 'r':
                        key = 'r';
                        board.s.setText("Mode: Viewing");
                        if (point == drawRepository.size()-1) {
                            key = 'q';
                            break;
                        }

                        gg.setColor(Color.WHITE); // Set the background color to white
                        gg.fillRect(0, 0, TopLevelDemo.paintImage.getWidth(), TopLevelDemo.paintImage.getHeight());

                        repaint();
                        break;
                    case 'c':
                        if (colourNumber < 8) {
                            colourNumber++;
                        }else {
                            colourNumber = 0;
                        }
                        switch (colourNumber) {
                            case 0: color = Color.BLACK; colourString = "BLACK"; break;
                            case 1: color = Color.RED; colourString = "RED"; break;
                            case 2: color = Color.GREEN; colourString = "GREEN"; break;
                            case 3: color = Color.BLUE; colourString = "BLUE"; break;
                            case 4: color = Color.MAGENTA; colourString = "MAGENTA"; break;
                            case 5: color = Color.YELLOW; colourString = "YELLOW"; break;
                            case 6: color = Color.CYAN; colourString = "CYAN"; break;
                            case 7: color = Color.PINK; colourString = "PINK"; break;
                        }
                        board.c.setText("Colour: " + colourString);
                        board.c.setBackground(color);
                        board.c.setForeground(new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue()));
                        break;
                    case '|':
                        write();
                        break;
                }
                board.repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                if (key == 'w') {
                    drawRepository.draw(point, e.getX(), e.getY(), bold, color);
                    moved(e.getX(),e.getY());
                }
            }
        });
    }

    private void moved(int x, int y) {
        this.x = x;
        this.y = y;
        repaint(this.x-bold, this.y-bold, bold*2, bold*2);
        System.out.println(x+","+y);
    }

    public Dimension getPreferredSize() {
        return new Dimension(500, 600);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);

        if (key == 'u') {
            point--;
            for (int i = 0; i <= point; i++) {
                for (int j = 0; j < drawRepository.get(i).size(); j++) {
                    x = drawRepository.getDraw(i, j).x();
                    y = drawRepository.getDraw(i, j).y();
                    bold = drawRepository.getDraw(i, j).bold();
                    gg.setColor(drawRepository.getDraw(i, j).color());
                    gg.fillRect(x-bold, y-bold, bold*2, bold*2);
                }
            }
            key = 'q';
        }else if (key == 'r') {
            point++;
            for (int i = 0; i <= point; i++) {
                for (int j = 0; j < drawRepository.get(i).size(); j++) {
                    x = drawRepository.getDraw(i, j).x();
                    y = drawRepository.getDraw(i, j).y();
                    bold = drawRepository.getDraw(i, j).bold();
                    gg.setColor(drawRepository.getDraw(i, j).color());
                    gg.fillRect(x-bold, y-bold, bold*2, bold*2);
                }
            }
        }else if (key == 'w') {
            gg.setColor(color);
            bold = boldDraw;
            gg.fillRect(x-bold, y-bold, bold*2, bold*2);
        }


        g.drawImage(TopLevelDemo.paintImage, 0, 0 ,null);

    }

    static public void write() {
        try {
            ImageIO.write(TopLevelDemo.paintImage, "png", new File("Drawing.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
