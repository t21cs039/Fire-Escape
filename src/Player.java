import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Player extends Objects{

	private int v;
	private int vx = 4;
	private int vy = -30;//ジャンプ速度
	private int size = Model.SCALE;
	private int width = Game.WIN_WIDTH;
	private int rightBound = width-size-4;
	private int leftBound = 1;
	private List<Tiles> tile = new LinkedList <>();
	private Image image;
	private Rectangle playerArea;
	private boolean jump;
	private boolean fall;
	private boolean doubleJump=false;
	private boolean left=false;
	private boolean right=false;
	
	//音を読み込み
	private WavSound jumping = new WavSound(getClass().getResource("jumpSound.wav"));//ジャンプ効果音
	private WavSound doubleJumping = new WavSound(getClass().getResource("doubleJumpSound.wav"));//2倍ジャンプ効果音

	public Player(int x, int y) {
		this.x=x;
		this.y=y;
		v=vy;
		setJump(false);
		setFall(false);
		setImage(player);

	}
	//プレイヤーの移動
	public void playerMove(String s) {
		
		switch(s) {
		case "a":
			moveLeft();
			break;
		case "d":
			moveRight();
			break;
		case "w":
			if(!jump ) {
				setJump(true);
				setJumping();
			}
			break;
		default: 
			break;
		}
	}
	
	public void isJumping() {
		
		if(isJump()) {
			if(v<30){
				y+=v;
				v=v+5;//ジャンプすると速度を変更する
				moving();//ジャンプするときの移動
				
				//足がタイルの上面とぶつかるとジャンプ停止
				if(isCollide()) {
					stopJumping();
				}
				
			}
			else {//速度が30以上になるとジャンプ停止
				stopJumping();
			}
		}
	}

	public void isFalling() {
		
		//ジャンプしいない、足にタイルがない場合はｆａｌｌがｔｒｕｅ
		if(!isJump() ) {
			if(!isCollide()) {
				setFall(true);
			}else {
				setFall(false);
				setJump(false);
			}
		}
		
		if(isFall()) {
			y+=10;//落とす速度は10
			moving();
		}
		
	}
	
	public void moveLeft() {
		if(x>leftBound) {
			x-=vx;
			left=true;
		}
		
	}
	
	public void moveRight() {
		if(x<rightBound) {
			x+=vx;
			right=true;
		}
		
	}
	
	public void moving() {
		if(left && x>leftBound) 
			x-=(vx+2);
		else if(right && x<rightBound)
			x+=(vx+4);
	}
	
	public void stopJumping() {
		setJump(false);
		setDoubleJump(false);
		v=vy;
	}
	
	public boolean isCollide() {
		Rectangle playerBottomArea = new Rectangle(x,y+Model.SCALE-1,size-10,size-Model.SCALE+1);//プレイヤーの足エリア
		for(Tiles t:tile) {
			if(t.getY()> y + 40) {//プレイヤーの下にあるタイルなら
				Rectangle tileUpperArea = new Rectangle(t.getX()+5,t.getY()-1,size*3 -30,1);//タイルの上面エリア
				if(playerBottomArea.intersects(tileUpperArea)) {
					return true;//ぶつかるとtrueに返す
				}
			}
		}
		return false;
	}
	
	public void check(List<Tiles> tile) {
		this.tile=tile;
		isJumping();
		isFalling();
		playerArea = new Rectangle(x+10,y-10,size-15,size-20);
		
		//leftとrightとdoubleJumpの再設定
		if(!isJump()) {
			left=false;
			right=false;
		}
		if(!isDoubleJump()) {
			setDoubleJump(false);
			setImage(player);
		}
	}
	
	public boolean getCoin(Coin c) {
		Rectangle coinArea = new Rectangle(c.getX()+5,c.getY()+10,size-40,size-30);//コインの面エリア
		return (playerArea.intersects(coinArea));//ぶつかるとtrueに返す
	}

	public boolean getStar(Star s) {
		Rectangle starArea = new Rectangle(s.getX()+5,s.getY()+10,size-40,size-30);//星の面エリア
		if(playerArea.intersects(starArea)){//ぶつかるとtrueに返す
			setDoubleJump(true);
			setJumping();
			return true;
		}
		return false;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(getImage(), x, y, size+10,size+10, null);
	}

	public boolean isDoubleJump() {
		return doubleJump;
	}
	
	public boolean isFall() {
		return fall;
	}
	
	public boolean isJump() {
		return jump;
	}

	public void setDoubleJump(boolean doubleJump) {
		this.doubleJump = doubleJump;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	public void setFall(boolean fall) {
		this.fall = fall;
	}
	
	public void setJumping() {
		if(doubleJump && isJump()) {
			v = -50;
			setImage(playerJump);
			doubleJumping.play();
		}
		else if(isJump()){
			jumping.play();
		}
		
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public boolean isHit(FireBall f) {
		Rectangle fireballArea = new Rectangle();
		if(f.getX()> x)
			fireballArea = new Rectangle(f.getX()+5,f.getY()+10,size-20,size-20);//星の面エリア
		else if(f.getX()<x)
			fireballArea = new Rectangle(f.getX()-5,f.getY()+10,size-20,size-20);//星の面エリア
		return playerArea.intersects(fireballArea);
	}

}
