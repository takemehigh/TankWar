import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	List<Wall> walls=new ArrayList<Wall>();
	Tank myTank = new Tank(500, 500, this,true);
	Tank enemyTank=new Tank(200,200,this,false);
	List<Missile> missiles = new ArrayList<Missile>();
	Image offScreenImage = null;
	List<Explode> explodes=new ArrayList<Explode>();
	List<Tank> tanks=new ArrayList<Tank>();
	public void paint(Graphics g) {
		g.drawString("missiles count:" + missiles.size(), 10, 50);
		g.drawString("explodeS:"+explodes.size(), 10, 70);
		g.drawString("tanks:"+tanks.size(), 10, 90);
		for(int i=0; i<missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.hitTank(tanks);
			m.hitTank(myTank);
			m.hitWall(walls);
			m.draw(g);
			
		}
		for (int i = 0; i < explodes.size(); i++) {
			Explode e=explodes.get(i);
			e.draw(g);
		}
		for(int i=0;i<tanks.size();i++){
			Tank t=tanks.get(i);
			t.touchWall(walls);
			t.draw(g);
			
		}
		myTank.touchWall(walls);
		myTank.draw(g);
		for(int i=0;i<walls.size();i++){
			Wall w=walls.get(i);
			System.out.println(i);
			w.draw(g);
		}
	}
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void lauchFrame() {
		//this.setLocation(400, 300);
		walls.add(new Wall(200,400,this));
		walls.add(new Wall(100,200,this));
		for(int i=0;i<10;i++){
			this.tanks.add(new Tank(false, this, 40+30*i, 200,Tank.Direction.D));
		}
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		
		this.addKeyListener(new KeyMonitor());
		
		setVisible(true);
		
		new Thread(new PaintThread()).start();
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();
	}
	
	private class PaintThread implements Runnable {

		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
		
	}
}













