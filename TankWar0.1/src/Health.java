import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Health {
	private int x,y,w,h;
	private int[][] pos={
			{300,600},{380,570},{340,540},{400,521},{341,511},{322,434},{370,421}
	};
	int step=0;
	private boolean live;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public Health() {
		this.x = pos[step][0];
		this.y = pos[step][1];
		this.w = 15;
		this.h = 15;
		this.live = true;
	}
	public void draw(Graphics g){
		if(!this.live){return;}
		if(step==pos.length){step=0;}
		Color c=g.getColor();
		g.setColor(Color.pink);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		move();
		
	}
	private void move() {
		if(step<pos.length-1){
		this.x = pos[step][0];
		this.y = pos[step][1];
		}
		step++;
	}
	public Rectangle getRec() {
		return new Rectangle(getX(),getY(),w,h);
	}
}
