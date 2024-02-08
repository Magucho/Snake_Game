//Edgar M Gómez P. 
// Back-end developer Java.
package gameSnake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener {

	
	static final int SIZE_WIDTH= 600;
	static final int SIZE_HEIGTH= 600;
	static final int UNIT_SIZE= 25;
	static final int GAME_UNITS= (SIZE_WIDTH * SIZE_HEIGTH)/UNIT_SIZE;
	static final int SPEED= 75;
	final int x[]= new int[GAME_UNITS];
	final int y[]= new int[GAME_UNITS];
	int bodyparts= 6;
	int applesEat;
	int appleX;
	int appleY;
	char direction = 'M';	
	boolean running= false;
	Timer time;
	Random random;

	public SnakePanel() {
		random= new Random();
		this.setPreferredSize(new Dimension(SIZE_WIDTH,SIZE_HEIGTH));
		this.setBackground(new Color(31,255,254));//
		this.setFocusable(true);
		this.addKeyListener(new MykeyAdapter());
		startGame();
	}
	//Inicio del juego
	public void startGame() {
		
		newApple();
		running= true;
		time= new Timer(SPEED,this);
		time.start();
	}
	//Muestra y presenta la ventana
	public void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		drow(g);
	}
	//Dibuja lo que necesitamos ver
	public void drow(Graphics g) {
		
		if(running) {
		   /*for(int i= 0; i<SIZE_HEIGTH/UNIT_SIZE; i++) {
			    g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SIZE_HEIGTH);
			    g.drawLine(0,i*UNIT_SIZE,SIZE_WIDTH,i*UNIT_SIZE);
		      }*///Divide en cuadrillas la ventana.
		        g.setColor(Color.PINK);
		        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
		      for(int i= 0; i< bodyparts; i++ ) {
			      if(i == 0) {
				     g.setColor(Color.white);
				     g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			      }
			       else {
				        g.setColor(new Color(45,180,0));
				        g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			          }}
		         g.setColor(Color.orange);
				g.setFont(new Font("Ink Free",Font.BOLD,40));
				FontMetrics metric= getFontMetrics(g.getFont());
				g.drawString("Puntaje: "+applesEat,(SIZE_WIDTH - metric.stringWidth("Puntaje: "+applesEat))/2,g.getFont().getSize());
		    }else {
		    	gameOver(g);
    }}
	//Mostrar manzana
	public void newApple() {
		
		appleX= random.nextInt((int)(SIZE_WIDTH/ UNIT_SIZE))* UNIT_SIZE;
		appleY= random.nextInt((int)(SIZE_HEIGTH/ UNIT_SIZE))* UNIT_SIZE;
	}
	//Mover la serpiente
	public void move() {
		
		for(int i= bodyparts; i>0; i--) {
			x[i]= x[i-1];
			y[i]= y[i-1];
		}
		
		switch(direction) {
		
		case 'U':
			y[0]= y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0]= y[0] + UNIT_SIZE;
			break;	
		case 'L':
			x[0]= x[0] - UNIT_SIZE;
			break;
		case 'M':
			x[0]= x[0] + UNIT_SIZE;
			break;	
	}}
	public void checkApple() {
		
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyparts++;
			applesEat++;
			newApple();
	}}
	//Si choca
	public void checkCollisions() {
		
		for(int i= bodyparts; i>0; i-- ) {
			if((x[0]== x[i]) && (y[0] == y[i])) {
				running= false;
		}}
		if(x[0] < 0) {
			running= false;
		}
		//si se sale por la derecha
		if(x[0] > SIZE_WIDTH) {
			running= false;
		}
		//si se sale por la izquierda
		if(y[0] < 0) {
			running= false;
		}
		//si se sale en la parte inferior
		if(y[0] > SIZE_HEIGTH) {
			running= false;
		}
		
		if(!running) {
			time.stop();
	}}
	//Finaliza el juego
	public void gameOver(Graphics g) {
		//puntos
		g.setColor(Color.orange);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metric= getFontMetrics(g.getFont());
		g.drawString("Puntaje: "+applesEat,(SIZE_WIDTH - metric.stringWidth("Puntaje: "+applesEat))/2,g.getFont().getSize());
		
		g.setColor(new Color(136,0,21));
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metric2= getFontMetrics(g.getFont());
		g.drawString("GAME OVER",(SIZE_WIDTH - metric2.stringWidth("GAME OVER"))/2,SIZE_HEIGTH/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MykeyAdapter extends KeyAdapter{
		
		@Override 
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			
			case KeyEvent.VK_LEFT:
				if(direction !='M') {
					direction= 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction !='L') {
					direction= 'M';
				}
				break;	
			case KeyEvent.VK_UP:
				if(direction !='D') {
					direction= 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction !='U') {
					direction= 'D';
				}
				break;	
}}}}
