package com.tank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall {
	int x;
	int y;
	public static final int WALL_WIDTH=100;
	public static final int WALL_HEIGHT=20;
	
	TankClient tc;
	
	public Wall(int x, int y, TankClient tc) {
		super();
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public Rectangle getRec(){
		return new Rectangle(x,y,WALL_WIDTH,WALL_HEIGHT);
	}
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(Color.BLACK);
		g.fillRect(x, y, WALL_WIDTH,WALL_HEIGHT);
		g.setColor(c);
	}
}
