import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BossState extends Cursor implements State {
	
	private PlayingState playingState;
	public static final int INSTRUCTION = 0;
	public static final int QUIT = 1;
	public static final int RESTART = 2;
	private Model model;

	public BossState(PlayingState playingState, Model model) {
		this.playingState = playingState;
		this.model = model;
		cursorValue = 0;
		pointer = 200;
	}

	@Override
	public State processTimeElapsed(int msec) {
		return this;
	}

	//ボスステートからメニューを選択できる
	@Override
	public State processKeyTyped(String typed) {
		if(typed.equals(" "))
			return this.playingState;
		else if(typed.equals("w") && cursorValue >0)
			cursorUp();
		else if (typed.equals("s") && cursorValue <2)
			cursorDown();
		else if(typed.equals("ENTER")) {
			if(cursorValue==INSTRUCTION) {//遊び方を表示
				return new Menu("Instruction.txt", this);
			}
			else if(cursorValue==QUIT) {//ゲーム終了
				System.exit(0);
			}
			else if(cursorValue==RESTART) {//ゲーム再始動
				return new TitleState(model);
			}
		}
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		playingState.paintComponent(g);
		g.setColor(Color.GRAY);
		drawBox(g);
		g.setColor(Color.WHITE);
		showMenu(g);
	}

	//メニュを表示する
	public void showMenu(Graphics g) {
		g.setFont(g.getFont().deriveFont(Font.BOLD, 32));
		g.drawString(">", 170, pointer);
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 32));
		g.drawString("HOW TO PLAY", 200, 200);
		g.drawString("QUIT", 260, 250);
		g.drawString("RESTART", 235, 300);
	}
	
	public void drawBox(Graphics g) {
		g.fill3DRect(190, 165, 200, 50, false);
		g.fill3DRect(190, 215, 200, 50, false);
		g.fill3DRect(190, 265, 200, 50, false);
	}

}
