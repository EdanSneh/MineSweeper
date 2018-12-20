import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Template extends JFrame {
    private int canvasWidth = 800;
    private int canvasHeight = 800;
    public static final int size = 40;
    private Canvas canvas;
    private Board board;
    private int length;
    private int width;
    private int bomb;

    public Template() {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        Container cp = getContentPane();
        cp.add(canvas);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        prompt();
        setTitle("Minesweeper");
        setVisible(true);
    }

    public void prompt() {
        JList list = new JList(new String[] {"Beginner 9x9 Mines: 10", "Intermediate 16x16 Mines: 40", "Expert 16x30 Mines: 99", "Custom (choose size below):"});
        JTextField lengthField = new JTextField();
        JTextField widthField = new JTextField();
        JTextField bombField = new JTextField();
        Object[] message = {
                list,
                "Length:", lengthField,
                "Width:", widthField,
                "Number of mines:", bombField,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (list.isSelectedIndex(0) || list.isSelectionEmpty()) {
                length = 9;
                width = 9;
                bomb = 10;
            } else if (list.isSelectedIndex(1)) {
                length = 16;
                width = 16;
                bomb = 40;
            } else if (list.isSelectedIndex(2)) {
                length = 16;
                width = 30;
                bomb = 99;
            }
            else {
                try {
                    length = Integer.parseInt(lengthField.getText());
                    width = Integer.parseInt(widthField.getText());
                    bomb = Integer.parseInt(bombField.getText());
                } catch (NumberFormatException e) {
                    prompt();
                }
            }
        } else {
            System.exit(0);
        }
        board = new Board(length, width, bomb, size);

    }

    private class Canvas extends JPanel implements MouseListener {

        public Canvas() {
            super();
            addMouseListener(this);

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.LIGHT_GRAY);
            board.draw(g);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (e.getButton() == 1) {
                if (x < board.length() * size && y < board.width() * size && board.checkSquare(x / size, y / size)) {
                    repaint();
                    if (board.isLose()) {
                        lose();
                    } else if (board.isWin()) {
                        win();
                    }
                }
            } else if (e.getButton() == 3) {
                if (x < board.length() * size && y < board.width()) {
                    repaint();
                    board.flag(x / size, y / size);
                }
            }
        }

        private void lose() {
            Object[] options = {
                    "New Game",
                    "Exit"
            };
            int n = JOptionPane.showOptionDialog(this,
                    "You Lose",
                    "Faliure",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 1) {
                System.exit(0);
            } else {
                board = new Board(length, width, bomb, size);
                repaint();
            }
        }

        public void win() {
            Object[] options = {
                    "New Game",
                    "Exit"
            };
            int n = JOptionPane.showOptionDialog(this,
                    "You Win!",
                    "Sucess",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 1) {
                System.exit(0);
            } else {
                board = new Board(length, width, bomb, size);
                repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}



        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Template();
            }
        });
    }

}
