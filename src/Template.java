import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Template extends JFrame {
    public static final int size = 30;
    private int canvasWidth = 16 * size + 4;
    private int canvasHeight = 16 * size + 26;
    private Canvas canvas;
    private Board board;
    private int length;
    private int width;
    private int bomb;

    public Template() {
        canvas = new Canvas();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        prompt("Welcome to Minesweeper\n\n");
        canvas.setPreferredSize(new Dimension(length * size, width * size));
        JScrollPane scrollPane = new JScrollPane(canvas);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(12);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        Container cp = getContentPane();
        cp.add(scrollPane);
        setMinimumSize(new Dimension(size, size));
        setMaximumSize(new Dimension(length * size + 20, width * size + 40));
        if (getMaximumSize().height < canvasHeight || getMaximumSize().width < canvasWidth) {
            setSize(getMaximumSize());
        } else {
            setSize(new Dimension(canvasWidth, canvasHeight));
        }
        setTitle("Minesweeper");
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        System.out.println(getSize());
    }

    public void prompt(String Question) {
        JList list = new JList(new String[] {"Beginner 9x9 Mines: 10", "Intermediate 16x16 Mines: 40", "Expert 30x16 Mines: 99", "Custom (choose size below):"});
        JTextField lengthField = new JTextField();
        JTextField widthField = new JTextField();
        JTextField bombField = new JTextField();
        Object[] message = {
                Question,
                list,
                "Length:", lengthField,
                "Width:", widthField,
                "Number of mines:", bombField,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Set Up", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (list.isSelectedIndex(0)) {
                length = 9;
                width = 9;
                bomb = 10;
            } else if (list.isSelectedIndex(1)) {
                length = 16;
                width = 16;
                bomb = 40;
            } else if (list.isSelectedIndex(2)) {
                length = 30;
                width = 16;
                bomb = 99;
            }
            else {
                try {
                    length = Integer.parseInt(lengthField.getText());
                    width = Integer.parseInt(widthField.getText());
                    bomb = Integer.parseInt(bombField.getText());
                } catch (NumberFormatException e) {
                    prompt("Please enter APPROPRIATE custom fields\n\n");
                    return;
                }
                if (bomb < 1) {
                    prompt("NOT ENOUGH BOMBS");
                    return;
                }
                if (length > 120 || width > 120) {
                    prompt("length and width too large (>120)");
                    return;
                }
                if (length * width - bomb - 9 < 0) {
                    prompt("TOO MANY BOMBS");
                    return;
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
            if (board.firstClick()) {
                if (e.getButton() == 1) {
                    if (x < board.length() * size && y < board.width() * size && board.checkSquare(x / size, y / size)) {
                        repaint();
                    }
                } else if (e.getButton() == 3) {
                    if (x < board.length() * size && y < board.width() * size && board.flag(x / size, y / size)) {
                        repaint();
                    }
                }
            } else {
                board.startGame(x / size, y / size);
                repaint();
            }
            if (board.isLose()) {
                end("You Lose", "Failure");
            } else if (board.isWin()) {
                end("You Win", "Success");
            }
        }

        private void end(String msg, String title) {
            Object[] options = {
                    "Change Difficulty",
                    "New Game",
                    "Quit"
            };
            int n = JOptionPane.showOptionDialog(this,
                    msg,
                    title,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == JOptionPane.CANCEL_OPTION) {
                System.exit(0);
            } else if (n == JOptionPane.NO_OPTION) {
                board = new Board(length, width, bomb, size);
            } else {
                prompt("Choose a difficulty\n\n");
            }
            repaint();
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
