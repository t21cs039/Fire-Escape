import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class FireBall extends Objects{
	
	private int vx;
	private int vy;
	private Image image;
	
	public FireBall(int x, int y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("fireball.png"));
	}
	
	public void update () {
		 x += vx;
		 y += vy;
	 }
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, x, y, Model.SCALE/2,Model.SCALE/2, null);
	}

}
