import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class TankClient extends Frame{
	/**
	 * 
	 */
	private void launchFrame(){
		this.setSize(800, 600);
		this.setLocation(300, 400);
		this.setTitle("Ì¹¿Ë´óÕ½0.1");
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
	}
	public static void main(String[] args) {
		new TankClient().launchFrame();
	}
	
}
