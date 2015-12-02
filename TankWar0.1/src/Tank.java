
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

public class Tank {
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	private boolean good;
	TankClient tc;
	boolean live=true;
	private int x, y,oldx,oldy;
	static Random r=new Random();
	private boolean bL=false, bU=false, bR=false, bD = false;
	enum Direction {L, LU, U, RU, R, RD, D, LD, STOP};
	
	private Direction dir = Direction.STOP;
	private Direction ptDir = Direction.D;
	private int step=r.nextInt(12)+3;
	public Tank(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public Tank(int x, int y, TankClient tc,boolean good) {
		this(x, y);
		this.tc = tc;
		this.setGood(good);
	}
	
	
	public Tank(boolean good, TankClient tc, int x, int y, Direction dir) {
		this(x,y,tc,good);
		this.dir = dir;
	}

	public void draw(Graphics g) {
		if(!this.isLive()){
			tc.tanks.remove(this);
			return;
		}
		Color c = g.getColor();
		if(isGood())g.setColor(Color.red);
		else g.setColor(Color.blue);
		g.fillOval(getX(), getY(), WIDTH, HEIGHT);
		g.setColor(c);
		
		switch(ptDir) {
		case L:
			g.drawLine(getX() + Tank.WIDTH/2, getY() + Tank.HEIGHT/2, getX(), getY() + Tank.HEIGHT/2);
			break;
		case LU:
			g.drawLine(getX() + Tank.WIDTH/2, getY() + Tank.HEIGHT/2, getX(), getY());
			break;
		case U:
			g.drawLine(getX() + Tank.WIDTH/2, getY() + Tank.HEIGHT/2, getX() + Tank.WIDTH/2, getY());
			break;
		case RU:
			g.drawLine(getX() + Tank.WIDTH/2, getY() + Tank.HEIGHT/2, getX() + Tank.WIDTH, getY());
			break;
		case R:
			g.drawLine(getX() + Tank.WIDTH/2, getY() + Tank.HEIGHT/2, getX() + Tank.WIDTH, getY() + Tank.HEIGHT/2);
			break;
		case RD:
			g.drawLine(getX() + Tank.WIDTH/2, getY() + Tank.HEIGHT/2, getX() + Tank.WIDTH, getY() + Tank.HEIGHT);
			break;
		case D:
			g.drawLine(getX() + Tank.WIDTH/2, getY() + Tank.HEIGHT/2, getX() + Tank.WIDTH/2, getY() + Tank.HEIGHT);
			break;
		case LD:
			g.drawLine(getX() + Tank.WIDTH/2, getY() + Tank.HEIGHT/2, getX(), getY() + Tank.HEIGHT);
			break;
		}
		
		move();
	}
	
	void move() {
		this.oldx=x;
		this.oldy=y;
		switch(dir) {
		case L:
			setX(getX() - XSPEED);
			break;
		case LU:
			setX(getX() - XSPEED);
			setY(getY() - YSPEED);
			break;
		case U:
			setY(getY() - YSPEED);
			break;
		case RU:
			setX(getX() + XSPEED);
			setY(getY() - YSPEED);
			break;
		case R:
			setX(getX() + XSPEED);
			break;
		case RD:
			setX(getX() + XSPEED);
			setY(getY() + YSPEED);
			break;
		case D:
			setY(getY() + YSPEED);
			break;
		case LD:
			setX(getX() - XSPEED);
			setY(getY() + YSPEED);
			break;
		case STOP:
			break;
		}
		if(getX()<0)setX(0);
		if(getY()<0)setY(0);
		if(getX()+WIDTH>tc.GAME_WIDTH)setX(tc.GAME_WIDTH-WIDTH);
		if(getY()+HEIGHT>tc.GAME_HEIGHT)setY(tc.GAME_HEIGHT-HEIGHT);
		if(this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}
		if(!this.isGood()){
			if(step==0){
			Direction[] dirs=Direction.values();
			int a=r.nextInt(dirs.length);
			this.dir=dirs[a];
			step=r.nextInt(12)+3;
			}
			step--;
			if(r.nextInt(40)>37)this.fire();
		}
	
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT :
			bL = true;
			break;
		case KeyEvent.VK_UP :
			bU = true;
			break;
		case KeyEvent.VK_RIGHT :
			bR = true;
			break;
		case KeyEvent.VK_DOWN :
			bD = true;
			break;
		}
		locateDirection();
	}
	
	public boolean isLive() {
		return live;
	}

	void locateDirection() {
		if(bL && !bU && !bR && !bD) dir = Direction.L;
		else if(bL && bU && !bR && !bD) dir = Direction.LU;
		else if(!bL && bU && !bR && !bD) dir = Direction.U;
		else if(!bL && bU && bR && !bD) dir = Direction.RU;
		else if(!bL && !bU && bR && !bD) dir = Direction.R;
		else if(!bL && !bU && bR && bD) dir = Direction.RD;
		else if(!bL && !bU && !bR && bD) dir = Direction.D;
		else if(bL && !bU && !bR && bD) dir = Direction.LD;
		else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_CONTROL:
			fire();
			break;
		case KeyEvent.VK_LEFT :
			bL = false;
			break;
		case KeyEvent.VK_UP :
			bU = false;
			break;
		case KeyEvent.VK_RIGHT :
			bR = false;
			break;
		case KeyEvent.VK_DOWN :
			bD = false;
			break;
		}
		locateDirection();		
	}
	
	public Missile fire() {
		if(!this.isLive()){return null;}
		int x = this.getX() + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.getY() + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m = new Missile(x, y, ptDir,tc,this.isGood());
		tc.missiles.add(m);
		return m;
	}

	public Rectangle getRec() {
		return new Rectangle(getX(),getY(),WIDTH,HEIGHT);
	}
	public boolean touchWall(Wall w){
		if(this.live&&this.getRec().intersects(w.getRec())){
			x=oldx;
			y=oldy;
			return true;
		}
		return false;
	}
	public void touchWall(List<Wall> walls){
		for(Wall w:walls){
			touchWall(w);
		}
	}
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

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}
}
