import java.awt.Graphics;

public class Coin extends Objects{
	
	public Coin(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//アイテムを表示する
	public void paintComponent(Graphics g) {
		g.drawImage(coin, x, y, Model.SCALE/2,Model.SCALE/2, null);
	}

}
