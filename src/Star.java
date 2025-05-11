import java.awt.Graphics;

public class Star extends Objects {
	
	public Star(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(special, x, y, Model.SCALE/2,Model.SCALE/2, null);
	}

}
