import java.awt.Font;
import java.awt.Graphics;

public class Score extends Object{
	private int gameScore;
	
	//スコアを上げる
	public void add() {
		gameScore = gameScore + 20;//アイテムのスコアを足す
	}

	public int getScore() {
		return gameScore;
	}
	
	public void showScore(Graphics g) {
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 16));
		g.drawString("score:" + gameScore, 450, 50);
	}

	public void setTime(long time) {
		int gameTime = (int) time / 1000 ;
		gameScore+= 1000 / gameTime;//生き残った時間をスコアに足す
	}

}
