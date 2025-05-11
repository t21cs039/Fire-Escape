import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

public class PlayingState implements State {
	
	private Model model;
	private Player player;
	private Fire fire;
	private FireBallCreate fireballCreate = new FireBallCreate();
	private ItemCreate itemCreate = new ItemCreate();
	private TileCreate tileCreate = new TileCreate();
	private Goal goal = new Goal();
	private Result result = new Result();
	private Score score = new Score();
	private Enemy enemy = new Enemy();
	private List <Tiles> tile = new LinkedList <>();
	private List <FireBall> fireball = new LinkedList <>();
	private List <Coin> coins = new LinkedList <>();
	private List <Star> stars = new LinkedList <>();
	private int time;
	private int level;
	private static final int FINALLEVEL = 10;
	private long startTime;
	private Difficulty difficulty = Difficulty.getInstance();
	private WavSound item = new WavSound(getClass().getResource("itemSound.wav"));//アイテムを習得効果音

	public PlayingState(Model model) {
		this.model=model;
		tile.add(new Tiles(250,400));
		player = new Player (300 , 350);
		fire = new Fire(0,400,20);
		startTime = System.currentTimeMillis();	
		itemCreate.createCoin(coins, tile);
		tileCreate.tileCreate(tile);
		fireballCreate.fireballCreate(fireball);
	}

	@Override
	public State processTimeElapsed(int msec) {
		time++;
		player.check(tile);
		fire.firemove();
		
		updateCoin();
		updateStar();
		updateFireBall();
		updateLevel();
		
		for (FireBall f:fireball)
			f.update();
		
		checkResult();
		
		if(result.isLose() || result.isWin()) {
			return new ResultState(model, result, score);
		}
		else
			return this;
	}

	@Override
	public State processKeyTyped(String typed) {

		//スペースキーでボスが来た画面を表示する
		if(typed.equals(" "))
			return new BossState(this, model);
		player.playerMove(typed);
		return this;
		
	}
	
	public void updateCoin() {
		LinkedList <Coin> coinss = new LinkedList <>();
		//コイン習得したら、スコアを上げる
		for(Coin c:coins) {
			if(!player.getCoin(c) && c.getY()<400) 
				coinss.add(c);
			else if(player.getCoin(c)){
				playSound(true);
				score.add();
			}
		}
		coins=coinss;
	}
	
	public void updateStar() {

		LinkedList <Star> starss = new LinkedList <>();
		//コイン習得したら、スコアを上げる
		for(Star s:stars) {
			if(!player.getStar(s) && s.getY()<400) 
				starss.add(s);
			else
				playSound(true);
		}
		stars = starss;
		
	}
	
	private void updateLevel() {
		
		if(level<FINALLEVEL) {
			scroll();
			itemCreate.createStar(coins,stars,time);
			if(time%difficulty.getTime()==0) {//難易度による秒ごとに火が上に動く
				fire.update();
			}
		}
		else if (level==FINALLEVEL) {//最後のステージを作成する
			createFinalLevel();
			}
		else {
			if(fireball.size()<difficulty.getFireballSize()+1) {//敵の方から火を出す
				enemy.getFireBall(fireball);
			}
		}
	}

	//最後のレベルを作成する
	public void createFinalLevel() {
		tileCreate.finalCreate(tile);
		scroll();
	}

	public void checkResult() {
		//負けると終了
		if(player.getY() > fire.getY()) 
			result.setLose(true);
		else if(level>FINALLEVEL && goal.arrivedGoal(player)) {
			long endTime = System.currentTimeMillis();
			result.setWin(true);
			score.setTime(endTime - startTime);
		}
	}

	private void playSound(boolean play) {
		if(play) 
			item.play();
	}

	public void scroll() {
		//スクロールのチェック
		Scroll scroll = new Scroll(player, tile, fire,fireball,coins, stars);
		
		//全てのオブジェクトをスクロールする
		if(scroll.isScroll()) {
			level++; 
			//スクリーン外に移動したタイルを削除
			if(level<FINALLEVEL) {
				updateTile();
				tileCreate.tileCreate(tile);
			}
			if(level <= FINALLEVEL)
				itemCreate.createCoin(coins,tile);
		}
	}

	public void updateFireBall() {
		//スクリーン外に移動したタイルを削除
		List <FireBall> fireballs = new LinkedList <>();
		for (FireBall f:fireball) {
			if(!(f.getY()>400 || f.getX()<0 || f.getX()>600 || player.isHit(f))) {
				fireballs.add(f);
			}
			if(player.isHit(f))
				result.setLose(true);
		}	
		fireball = fireballs;
		fireballCreate.fireballCreate(fireball);
	}

	public void updateTile() {
		List <Tiles> tiles = new LinkedList <>();
		for (Tiles t:tile) {
			if(t.getY()<500) {
				tiles.add(t);
			}
		}
		tile = tiles;//タイルを更新
	}

	@Override
	public void paintComponent(Graphics g) {
		for (Tiles t:tile) 
			t.paintComponent(g);//タイルを表示する
		
		for(Coin c:coins)
			c.paintComponent(g);//コインを表示する
		for(Star s:stars)
			s.paintComponent(g);//ジャンプアイテムを表示する
		
		if(level>FINALLEVEL) {
			goal.paintComponent(g);
			enemy.paintComponent(g);
		}
		
		player.paintComponent(g);//プレイヤーを表示する
		
		for (FireBall f:fireball)
			f.paintComponent(g);//飛ぶ火を表示する
		
		fire.paintComponent(g);//火を表示する
		score.showScore(g);//スコアを表示する
	}

}
