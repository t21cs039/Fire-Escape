import java.awt.Graphics;
import java.util.List;

public class Enemy extends Objects {
	
	public Enemy() {
		x=50;
		y=50;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(enemy, x, y, Model.SCALE, Model.SCALE, null);
	}

	public void getFireBall(List <FireBall> fireball) {
		FireBall f1 = new FireBall(50,100, 4, 20);
		fireball.add(f1);
		FireBall f2 = new FireBall(50,100, 8, 15);
		fireball.add(f2);
		FireBall f3 = new FireBall(50,100, 12, 10);
		fireball.add(f3);
		FireBall f4 = new FireBall(50,100, 16, 5);
		fireball.add(f4);
	}
}
