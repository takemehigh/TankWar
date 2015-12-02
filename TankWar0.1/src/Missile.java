import java.awt.*;

public class Missile {
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	boolean live=true;
	int x, y;
	Tank.Direction dir;
	TankClient tc;
	public Missile(int x, int y, Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	

	
	public Missile(int x, int y, Tank.Direction dir,TankClient tc) {
		this(x, y, dir);
		this.tc=tc;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(Color.black);
		g.fillOval(x, y, 10, 10);
		g.setColor(c);
		move();
		if(!this.isLive()){
			tc.missiles.remove(this);
		}
	}
	public void move(){
		switch (dir) {
		case U:
		y-=YSPEED;	
		break;
		case D:
		y+=YSPEED;
		break;
		case L:
		x-=XSPEED;
		break;
		case R:
		x+=XSPEED;
		break;
		case LU:
		x-=XSPEED;
		y-=YSPEED;
		break;
		case RU:
		x+=XSPEED;
		y-=YSPEED;
		break;
		case LD:
		x-=XSPEED;
		y+=YSPEED;
		break;
		case RD:
		x+=XSPEED;
		y+=YSPEED;
		break;
		
		}
		if(x<0||y<0||x>TankClient.GAME_WIDTH||y>TankClient.GAME_HEIGHT){
			this.setLive(false);
		}
	}
}

	
	

