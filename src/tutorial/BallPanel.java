package tutorial;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;



public class BallPanel extends JPanel implements MouseListener{
	private static final int REFRESH_RATE = 30;
	private Ball ball;
	private List<Ball> balls;
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
	
	public BallPanel(int _width, int _height) {
		this.areaWidth = _width;
		this.areaHeight = _height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		Random random = new Random();
		int radius = 10;
		int speed = 5;
		int angleInDegree = random.nextInt(360);		
		this.balls = new ArrayList<>();
		
		for(int i=0;i<5;i++) {
			int x = (int) (random.nextInt((int) (_width-radius*2-20)) + radius + 10);
			int y = (int) (random.nextInt((int)(_height-radius*2-20)) + radius + 10);
			this.balls.add(new Ball(x, y, radius, speed, angleInDegree));
		}
		
		box = new BallArea(0, 0, _width, _height, Color.BLACK, Color.WHITE);
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				Dimension dim = c.getSize();
				areaWidth = dim.width;
				areaHeight = dim.height;
				box.set(0, 0, _width, _height);
			}
		});
		addMouseListener(this);
		startThread();
		
	}
	
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while(true) {
//					ball.collideBall(balls);
					collideAll(box);
					repaint();
					try {
						Thread.sleep(1000/REFRESH_RATE);
					}catch(InterruptedException ex) {}
		
				}
			}
		};
		gameThread.start();
	}
	
	public void collideAll(BallArea boxArea) {
		for(Ball ball : balls) {
			ball.collide(boxArea);
			for(Ball ball_ : balls) {
				if(ball != ball_) {
					ball.collideBall(ball_);
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		box.draw(g);
		for(Ball ball : balls) {
			ball.draw(g);
		}
	}
	
	private int angleInDegree() {
		Random rand = new Random();
		return rand.nextInt(360);
	}
	
	public void addBall(int x, int y) {
		int radius = new Random().nextInt(20, 70);
		int speed = new Random().nextInt(3, 7) ;
		
		balls.add(new Ball(x, y, radius, speed, angleInDegree()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		addBall(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	



	
}
