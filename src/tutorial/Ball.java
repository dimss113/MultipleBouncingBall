package tutorial;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

public class Ball {
	float x, y;
	public float speedX, speedY;
	int speed;
	float radius;
	private Color color;
	private int colorRed;
	private int colorGreen;
	private int colorBlue;
	
	public Ball(float _x, float _y, float _radius, float _speed, float _angleInDegree) {
		this.x = _x;
		this.y = _y;
		this.speedX = (float)(_speed * Math.cos(Math.toRadians(_angleInDegree)));
		this.speedY = (float)(_speed * Math.sin(Math.toRadians(_angleInDegree)));
		this.radius = _radius;
		this.colorRed = new Random().nextInt(255);
		this.colorGreen = new Random().nextInt(255);
		this.colorBlue = new Random().nextInt(255); 
		this.color = new Color(colorRed, colorGreen, colorBlue);

	}	
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval((int)(x-radius), (int)(y-radius), (int)(2*radius), (int)(2*radius));
	}
			
	public void collide(BallArea box) {
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;
		x += speedX;
		y += speedY;
			
		if(x < ballMinX) {
			speedX =  -speedX;
			x = ballMinX;
		}else if(x > ballMaxX) {
			speedX = -speedX;
			x = ballMaxX;
		}
			
		if(y < ballMinY) {
			speedY = -speedY;
			y = ballMinY;
		}else if(y > ballMaxY) {
			speedY = -speedY;
			y = ballMaxY;
		}

	}
	
//	public void collideBall(Ball ball, List<Ball>balls) {
//		for(int i=0;i<10;i++) {
//			
//			if(!ball.equals(balls.get(i))) {
//				
//				// dari kanan bola saat ini
//				if(Math.sqrt(Math.pow(balls.get(i).x - radius - (ball.x + radius), 2)) < radius) {
//					ball.speedX = -ball.speedX;
////					ball.speedY = -ball.speedY;
//					ball.y = ball.y;
//					ball.x = (ball.x + radius) - (balls.get(i).x - radius); 
//				}
//				
//				// dari atas bola saat ini
//				else if(Math.sqrt(Math.pow(balls.get(i).y - radius - (ball.y + radius), 2)) < radius) {
////					ball.speedX = -ball.speedX;
//					ball.speedY = -ball.speedY;
//					ball.x = ball.x;
//					ball.y = (ball.y + radius) - (balls.get(i).y - radius);
//				}
//				
//				// dari kiri bola saat ini
//				else if(Math.sqrt(Math.pow(balls.get(i).x + radius - (ball.x - radius), 2)) < radius) {
//					ball.speedX = -ball.speedX;
////					ball.speedY = -ball.speedY;
//					ball.y = ball.y;
//					ball.x = (ball.x - radius) + (balls.get(i).x + radius);
//				}
//				
//				// dari bawah bola saat ini
//				else if(Math.sqrt(Math.pow(balls.get(i).y + radius - (ball.y - radius), 2)) < radius) {
//					ball.speedY = -ball.speedY;
////					ball.speedY = -ball.speedY;
//					ball.x = ball.x;
//					ball.y = (ball.y - radius) + (balls.get(i).y + radius);
//				}
//			}
//
//		}
//	}
	
	public void collideBall(Ball ball) {
		float bufferSpeedX;
		float bufferSpeedY;
		
		float distanceOfX = (ball.x - x);
		float distanceOfY = (ball.y - y);
		
		float distance = distanceOfX * distanceOfX + distanceOfY * distanceOfY;
		
		if(distance <= (ball.radius + radius) * (ball.radius + radius)) {
			if(distance < (ball.radius + radius) * (ball.radius + radius)) {
				double arcSin = distanceOfY/Math.sqrt(distance);
				double arcCos = distanceOfX/Math.sqrt(distance);
				
				x = (float) (ball.x - (arcCos * (radius + ball.radius)));
				y = (float) (ball.y - (arcSin * (radius + ball.radius)));
				
				bufferSpeedX = speedX;
				bufferSpeedY = speedY;
				
				speedX = ball.speedX;
				speedY = ball.speedY;
				
				ball.speedX = bufferSpeedX;
				ball.speedY = bufferSpeedY;
			}
		}
	}
	

}
