package gameSnake;

import javax.swing.JFrame;

public class SnakeWindows extends JFrame {

	public SnakeWindows(){
		
		this.add(new SnakePanel());
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
