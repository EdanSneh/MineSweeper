import java.awt.*;
import javax.swing.*;


public class frame extends JFrame{
	JButton bChange;
	
	frame(String title){
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		bChange = new JButton("3");
		add(bChange);
		
	}
}
