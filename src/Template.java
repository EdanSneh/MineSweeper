import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Template extends JFrame {
    private int canvasWidth = 640;
    private int canvasHeight = 480;

    private Canvas canvas;

    public Template() {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        Container cp = getContentPane();
        cp.add(canvas);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("temp");
        setVisible(true);
    }

    private class Canvas extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.LIGHT_GRAY);
            Board board = new Board(20, 20, 2);
            board.draw(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Template();
            }
        });
    }

}
