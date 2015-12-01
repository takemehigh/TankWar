import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Tank {
	TankClient tc;
	private static final int XSPEED=5;
	private static final int YSPEED=5;
	private int x;
	private int y;
	private boolean L,R,U,D=false;
	enum Direction {U,D,L,R,LU,RU,RD,LD,STOP};
	Direction dir=Direction.STOP;
	private Missile m;
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Tank(TankClient tc, int x, int y) {
		this(x,y);
		this.tc = tc;
		
	}

	void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(Color.red);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		move();
	}
	public void keyPressed(KeyEvent e) {
		
		int key=e.getKeyCode();
		System.out.println(key);
		switch (key) {
		case KeyEvent.VK_RIGHT:
			R=true;
			break;
		case KeyEvent.VK_LEFT:
			L=true;
			break;
		case KeyEvent.VK_UP:
			U=true;
			break;
		case KeyEvent.VK_DOWN:
			D=true;
			break;
		case KeyEvent.VK_CONTROL:
			tc.mymissle=fire();
			break;
		}
		decideDirection();
	}
	private Missile fire() {
		Missile m=new Missile(x, y, dir);
		return m;
	}
	private void decideDirection() {
		if(L&&!R&&!U&&!D)dir=Direction.L;
		else if(!L&&R&&!U&&!D)dir=Direction.R;
		else if(!L&&!R&&U&&!D)dir=Direction.U;
		else if(!L&&!R&&!U&&D)dir=Direction.D;
		else if(L&&!R&&U&&!D)dir=Direction.LU;
		else if(!L&&R&&U&&!D)dir=Direction.RU;
		else if(L&&!R&&!U&&D)dir=Direction.LD;
		else if(!L&&R&&!U&&D)dir=Direction.RD;
		else if (!L&&!R&&!U&&!D)dir=Direction.STOP;
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
		case STOP:
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			R=false;
			break;
		case KeyEvent.VK_LEFT:
			L=false;
			break;
		case KeyEvent.VK_UP:
			U=false;
			break;
		case KeyEvent.VK_DOWN:
			D=false;
			break;
		}
		decideDirection();
	}
	
}
