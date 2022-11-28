package tutorial;

import java.awt.Color;
import java.awt.Graphics;

public class BallArea {
	int minX;
	int maxX;
	int minY;
	int maxY;
	private Color colorFilled;
	private Color colorBorder;
	
	public BallArea(int _x, int _y, int _width, int _height, Color _colorFilled, Color _colorBorder) {
		this.minX = _x;
		this.minY = _y;
		this.maxX = _x + _width - 1;
		this.maxY = _y + _height - 1;
		this.colorFilled = _colorFilled;
		this.colorBorder = _colorBorder;
	}
	
	public void set(int _x, int _y, int _width, int _height) {
		this.minX = _x;
		this.minY = _y;
		maxX = _x + _width - 1;
		maxY = _y + _height - 1;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.colorFilled);
		g.fillRect(minX, minY, maxX-minX-1, maxX-minY-1);
		g.setColor(colorBorder);
		g.drawRect(minX, minY, maxX-minX-1, maxX-minY-1);
	}
	
}
