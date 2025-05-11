
public class Result {

	private boolean win;
	private boolean lose;
	
	public Result() {
		super();
		this.win = false;
		this.lose = false;
	}

	public boolean isWin() {
		return win;//真であれば勝つ
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean isLose() {
		return lose;//真であれば負ける
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
	
}
