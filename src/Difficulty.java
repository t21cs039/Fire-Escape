
public class Difficulty {
	
	private static final int EASY = 0;
	private static final int NORMAL = 1;
	private static final int HARD = 2;
	private int mode;
	
	// 唯一のインスタンスを保持（クラス変数）
	private static Difficulty difficulty;
	// コンストラクタを private にし，外部からのインスタンス生成を禁止（ここがミソ）
	private Difficulty() {}
	
	public static Difficulty getInstance() {
		if(difficulty==null)
			difficulty = new Difficulty();
		return difficulty;
		}
	
	public int getEasy() {
		return EASY;
	}
	public int getNormal() {
		return NORMAL;
	}
	public int getHard() {
		return HARD;
	}
	
	public int getMode() {
		return mode;
	}

	public void reduce() {
		mode-=1;
	}

	public void increase() {
		mode+=1;
	}

	//難易度によって火を上に移動する時間が変わる
	public int getTime() {
		int time=0;
		if(mode==EASY)
			time=30;
		else if (mode==NORMAL)
			time=20;
		else
			time=10;
		return time;
	}

	//難易度によって攻撃アイテムのサイズ変わる
	public int getFireballSize() {
		int fireballSize=0;
		if(mode==EASY)
			fireballSize=1;
		else if (mode==NORMAL)
			fireballSize=2;
		else
			fireballSize=4;
		return fireballSize;
	}	

}
