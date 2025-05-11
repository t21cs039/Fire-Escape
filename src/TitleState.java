import java.awt.*;

public class TitleState extends Cursor implements State {

	private Model model;
	private static final int START = 0;
	private static final int INSTRUCTION = 1;
	private static final int RANKING = 2;
	private static final int EXIT = 3; 
	private Difficulty difficulty = Difficulty.getInstance();

	public TitleState(Model model) {
		this.model = model;
		cursorValue = 0;
		pointer = 200;
	}

	@Override
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		if(typed.equals("w") && cursorValue >0)
			cursorUp();
		else if (typed.equals("s") && cursorValue <3)
			cursorDown();
		//難易度の選択
		else if(typed.equals("a") && difficulty.getMode()>0 && cursorValue==START)
			difficulty.reduce();
		else if(typed.equals("d") && difficulty.getMode()<2 && cursorValue==START)
			difficulty.increase();
		//メニュを選択
		else if(typed.equals("ENTER")) {
			if (cursorValue==START)
				return new PlayingState(model);
			else if (cursorValue==INSTRUCTION)
				return new Menu("Instruction.txt", this);
			else if(cursorValue==RANKING)
				return new Menu("Ranking.txt", this);
			else if(cursorValue==EXIT)
				System.exit(0);
		}		
		
		return this;
	}

	//メニュを表示する
	public void showTitle(Graphics g) {
		g.setFont(g.getFont().deriveFont(Font.BOLD));
		g.drawString(">", 170, pointer);
		g.setFont(model.getView().getTitleFont());
		g.drawString("FIRE ESCAPE", 120, 120);
		g.setFont(model.getView().getCustomFont());
		g.drawString("< "+ mode() +" >", 210, 200);
		g.drawString("HOW TO PLAY", 200, 250);
		g.drawString("RANKING", 235, 300);
		g.drawString("QUIT", 260, 350);
	}

	//難易度の言葉を返す
	public String mode() {
		int mode = difficulty.getMode();
		if(mode==difficulty.getNormal())
			return "NORMAL";
		else if(mode==difficulty.getHard())
			return " HARD ";
		return " EASY ";
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.GRAY);
		drawBox(g);
		g.setColor(Color.WHITE);
		showTitle(g);
	}

	public void drawBox(Graphics g) {
		g.fill3DRect(190, 165, 200, 50, false);
		g.fill3DRect(190, 215, 200, 50, false);
		g.fill3DRect(190, 265, 200, 50, false);
		g.fill3DRect(190, 315, 200, 50, false);
		
	}
	

}
