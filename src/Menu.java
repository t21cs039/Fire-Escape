import java.awt.*;

public class Menu implements State {

	private Reader reader;
	private State state;

	public Menu(String filename, State state) {
		this.state = state;
		reader = new Reader(filename);//ファイルを読み取る
	}
	public State processTimeElapsed(int msec) {
		return this;
	}

	public State processKeyTyped(String typed) {
		//タイトル画面に戻る
		if(typed.equals("ENTER"))
			return state;
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		drawBox(g);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 16));
		g.setColor(Color.WHITE);
		reader.read(g);
	}
	
	public void drawBox(Graphics g) {
		
		Color c = new Color(0,0,0,200);
		g.setColor(c);
		g.fillRoundRect(150, 80, 300, 350, 35, 35);
		
		c = new Color(255,255,255);
		g.setColor(c);
		g.drawRoundRect(150, 80, 300, 350, 25, 25);
	}
	

}
