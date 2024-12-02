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
import java.util.ArrayList;

class MyPanel extends JPanel {

    private int x = -20;
    private int y = -20;
    private char key = 'q';
    public int bold = 2;
    public int boldDraw = 2;
    private ArrayList<ArrayList> xList = new ArrayList<>();
    private ArrayList<ArrayList> yList = new ArrayList<>();
    private ArrayList<ArrayList> boldList = new ArrayList<>();
    private ArrayList<ArrayList> colourList = new ArrayList<>();
    private int point = -1;
    private Color colour = Color.BLACK;
    private Color colorDraw = Color.BLACK;
    private int colourNumber = 0;
    private String colourString = new String();
    Graphics2D gg = TopLevelDemo.paintImage.createGraphics();

    public MyPanel(Board board) {
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
                        if (point == xList.size()) {
                            xList.add(new ArrayList<Integer>());
                            yList.add(new ArrayList<Integer>());
                            boldList.add(new ArrayList<Integer>());
                            colourList.add(new ArrayList<Integer>());
                        }
                        if (!xList.get(point).isEmpty()) {
                            xList.get(point).clear();
                            yList.get(point).clear();
                            boldList.get(point).clear();
                            colourList.get(point).clear();
                            for (int i = point+1; i < xList.size(); i++) {
                                xList.remove(i);
                                yList.remove(i);
                                boldList.remove(i);
                                colourList.remove(i);
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
                        if (point == xList.size()) {
                            xList.add(new ArrayList<Integer>());
                            yList.add(new ArrayList<Integer>());
                            boldList.add(new ArrayList<Integer>());
                            colourList.add(new ArrayList<Integer>());
                        }
                        if (!xList.get(point).isEmpty()) {
                            xList.get(point).clear();
                            yList.get(point).clear();
                            boldList.get(point).clear();
                            colourList.get(point).clear();
                            for (int i = point+1; i < xList.size(); i++) {
                                xList.remove(i);
                                yList.remove(i);
                                boldList.remove(i);
                                colourList.remove(i);
                            }
                        }
//                        x = y = -2 * bold;
                        xList.get(point).add(300);
                        yList.get(point).add(300);
                        boldList.get(point).add(350);
                        colourList.get(point).add(Color.WHITE);

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
                        if (point == xList.size()-1) {
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
                            case 0: colorDraw = Color.BLACK; colourString = "BLACK"; break;
                            case 1: colorDraw = Color.RED; colourString = "RED"; break;
                            case 2: colorDraw = Color.GREEN; colourString = "GREEN"; break;
                            case 3: colorDraw = Color.BLUE; colourString = "BLUE"; break;
                            case 4: colorDraw = Color.MAGENTA; colourString = "MAGENTA"; break;
                            case 5: colorDraw = Color.YELLOW; colourString = "YELLOW"; break;
                            case 6: colorDraw = Color.CYAN; colourString = "CYAN"; break;
                            case 7: colorDraw = Color.PINK; colourString = "PINK"; break;
                        }
                        board.c.setText("Colour: " + colourString);
                        board.c.setBackground(colorDraw);
                        board.c.setForeground(new Color(255 - colorDraw.getRed(), 255 - colorDraw.getGreen(), 255 - colorDraw.getBlue()));
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
                    xList.get(point).add(e.getX());
                    yList.get(point).add(e.getY());
                    boldList.get(point).add(bold);
                    colourList.get(point).add(colorDraw);
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
                for (int j = 0; j < xList.get(i).size(); j++) {
                    x = (int) xList.get(i).get(j);
                    y = (int) yList.get(i).get(j);
                    bold = (int) boldList.get(i).get(j);
                    colour = (Color) colourList.get(i).get(j);
                    gg.setColor(colour);
                    gg.fillRect(x-bold, y-bold, bold*2, bold*2);
                }
            }
//            x = y = -2 * bold;
            key = 'q';
        }else if (key == 'r') {
            point++;
            for (int i = 0; i <= point; i++) {
                for (int j = 0; j < xList.get(i).size(); j++) {
                    x = (int) xList.get(i).get(j);
                    y = (int) yList.get(i).get(j);
                    bold = (int) boldList.get(i).get(j);
                    colour = (Color) colourList.get(i).get(j);
                    gg.setColor(colour);
                    gg.fillRect(x-bold, y-bold, bold*2, bold*2);
                }
            }
        }else if (key == 'w') {
            gg.setColor(colorDraw);
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
