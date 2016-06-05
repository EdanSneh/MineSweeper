import javax.swing.JFrame;

public class core {
	public static void main(String[] args) {
		init();
	}

	private static void init() {
		frame board = new frame("MineSweeper");
		board.setSize(800, 600);
		//board.pack();
		board.setVisible(true);
	}
	
	
}
