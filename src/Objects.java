import java.awt.Image;
import java.awt.Toolkit;

public class Objects {

	//アイテムの座標
	protected int x;
	protected int y;
	
	//コインの画像
	protected Image coin = Toolkit.getDefaultToolkit().getImage(getClass().getResource("scroll.png"));
	//アイテムの画像
	protected Image special = Toolkit.getDefaultToolkit().getImage(getClass().getResource("star.png"));
	//ゴールの画像読み込み
	protected Image goal = Toolkit.getDefaultToolkit().getImage(getClass().getResource("goal.png"));
	//摘ーの画像読み込み
	protected Image enemy = Toolkit.getDefaultToolkit().getImage(getClass().getResource("dino.png"));
	//プレイヤーの画像読み込み
	protected Image player = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ninja.png"));
	protected Image playerJump = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ninjajump.png"));
	
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
	
	public void scroll(int i) {
		y+=i;
	}

}
