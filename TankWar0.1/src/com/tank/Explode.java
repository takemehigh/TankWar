package com.tank;
import java.awt.Color;
import java.awt.Graphics;

public class Explode {
	boolean live=true;
	int x;
	int y;
	int step=0;
	int[] d={4,8,16,32,64,32,16,8,4};
	TankClient tc;
	
	public Explode(int x, int y,TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc=tc;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public void draw(Graphics g){
		if(!this.isLive()){
			tc.explodes.remove(this);
		}
		if(step==d.length){
			step=0;
			this.setLive(false);
		}

		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, d[step], d[step]);
		g.setColor(c);
		step++;
	}
}
