import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ResultState extends Cursor implements State {

	private Model model;
	private static final int RESTART = 0;
	private static final int EXIT = 1;
	private Result result;
	private Score score;

	public ResultState(Model model, Result result, Score score) {
		this.model=model;
		this.result = result;
		this.score = score;
		cursorValue=0;
		pointer=350;
		new Writer("Ranking.txt", score);
	}

	@Override
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		switch(typed) {
		case "w":
			if(cursorValue==1) {
				cursorUp();
			}
			break;
		case "s":
			if(cursorValue==0) {
				cursorDown();
			}
			break;
		default:
			break;
		}
		if(typed.equals("ENTER")) {
			//タイトル画面に戻る
			if(cursorValue == RESTART)
				return new TitleState(model);
			//ゲーム終了
			else if(cursorValue == EXIT)
				System.exit(0);
		}
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.GRAY);
		drawBox(g);
		g.setColor(Color.WHITE);
		drawResult(g);

	}
	
	//結果画面を表示する
	public void drawResult(Graphics g) {
		g.setFont(g.getFont().deriveFont(Font.BOLD, 40));
		g.drawString("RESULT", 220, 100);
		if(result.isLose()) {
			g.drawString("GAME OVER", 200, 200);
		}
		else if(result.isWin()) {
			g.drawString("GAME CLEAR!", 200, 200);
		}
		g.setFont(model.getView().getCustomFont());
		g.setFont(g.getFont().deriveFont(Font.BOLD));
		g.drawString(">",170 , pointer);
		g.setFont(g.getFont().deriveFont(Font.PLAIN));
		g.drawString("SCORE:"+score.getScore(), 220, 250);
		g.drawString("RESTART", 235, 350);
		g.drawString("QUIT", 260, 400);
		
	}

	public void drawBox(Graphics g) {
		g.fill3DRect(190, 315, 200, 50, false);
		g.fill3DRect(190, 365, 200, 50, false);
	}

}
