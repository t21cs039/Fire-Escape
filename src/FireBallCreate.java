import java.util.List;

public class FireBallCreate {

	private int x;
	private int vx;
	private int y=250;
	
	//攻撃アイテムを生成する
	public void fireballCreate(List<FireBall> fireball) {
		int max = 10;
		int min = 8;
		Difficulty difficulty = Difficulty.getInstance();
		RandNumGenerator r = RandNumGenerator.getInstance();
	
		while(fireball.size()<difficulty.getFireballSize()) {
			
			//速度がゼロでない場合は生成する
			if(vx!=0 && y>0) {
				FireBall f = new FireBall(x, y, vx, 0);
				fireball.add(f);
				y-=100;
			}
			
			//y座標を再設定する
			if(y<0) {
				y=250;
			}
			
			if(x==0) {
				x=500;//次は右から飛ぶ
				vx=-(r.nextInt(max+min-1)+min );
			}else {
				x=0;//次は左から飛ぶ
				vx= r.nextInt(max+min-1)+min ;
			}
			
		}
		
	}
	
}
