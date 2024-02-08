//Edgar M Gómez P. 
// Back-end developer Java.
package gameSnake;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SnakeWindows extends JFrame {

	public SnakeWindows(){
		
		this.setIconImage(getIconImage());
		this.add(new SnakePanel());
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true); // Quita la pestaña de cierre.
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
	
	public Image getIconImage(){
        return Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("image/snake.png")).getScaledInstance(100,100,20);
    }
}
