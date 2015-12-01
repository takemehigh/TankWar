import java.awt.Color;
import java.awt.Graphics;

public class Missile {
	private int x;
	private int y;
	private static final int XSPEED=10;
	private static final int YSPEED=10;
	private boolean L,R,U,D=false;
	enum Direction {U,D,L,R,LU,RU,RD,LD};
	Tank.Direction dir;
	
	public Missile(int x, int y,Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir=dir;
	}

	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(Color.black);
		g.fillOval(x, y, 10, 10);
		g.setColor(c);
		move();
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
	}
}
