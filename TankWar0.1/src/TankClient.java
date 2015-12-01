import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame{
	/**
	 * 
	 */
	static final int SPEEDX=5;
	static final int SPEEDY=5;
	
	Image bufferImage=null;
	static final int GAME_WIDTH=640;
	static final int GAME_HEIGHT=480;
	Tank mytank=new Tank(this,50, 50);
	Missile mymissle=null;
	@Override
	public void update(Graphics g) {
		if(bufferImage==null){
			bufferImage=this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics bufferg=bufferImage.getGraphics();
		Color c=bufferg.getColor();
		bufferg.setColor(Color.GREEN);
		bufferg.fillRect(0, 0,GAME_WIDTH ,GAME_HEIGHT);
		bufferg.setColor(c);
		paint(bufferg);
		g.drawImage(bufferImage, 0, 0, null);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if(mymissle!=null)mymissle.draw(g);
		mytank.draw(g);
		
	}
	private void launchFrame(){
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setLocation(300, 400);
		this.setTitle("Ì¹¿Ë´óÕ½0.1");
		this.setResizable(false);
		this.setVisible(true);
		this.setBackground(Color.gray);
		this.addKeyListener(new KeyMonitor());
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		new Thread(new PaintThread()).start();
	}
	public static void main(String[] args) {
		new TankClient().launchFrame();
	}
	private class PaintThread implements Runnable{
		public void run() {
			while(true){
				repaint();
		
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		
	}
	private class KeyMonitor extends KeyAdapter{

		public void keyPressed(KeyEvent e) {
			mytank.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			mytank.keyReleased(e);
		}
		
	}
}
