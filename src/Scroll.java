import java.util.List;

public class Scroll {

	private boolean scrolling=false;
	private Player player;
	private List <Tiles> tile;
	private Fire fire;
	private List <FireBall> fireball;
	private List<Coin> coin;
	private List<Star> star;
	
	public Scroll(Player player, List<Tiles> tile2, Fire fire, List<FireBall> fireball2, List<Coin> coins, List<Star> stars) {
		this.player=player;
		this.tile=tile2;
		this.fire=fire;
		this.fireball=fireball2;
		this.coin = coins;
		this.star = stars;
		scroll();
	}

	public void scroll () {
		
		scrolling=false;
		//地面にいるとき
		//y座標が200以上であればスクロールする
		if( !player.isFall() && (player.getY() < 200)) {
			scrolling = true;
			player.scroll(100);
				
			for (Tiles t:tile) 
				t.setY(t.getY()+ 100);//タイルのスクロール
			
			for (FireBall f:fireball)
				f.scroll(100);//攻撃アイテムのスクロール
			
			for(Coin c:coin)
				c.scroll(100);//アイテムのスクロール
			
			for(Star s:star)
				s.scroll(100);//アイテムのスクロール
				
			fire.scroll();//火のスクロール
		}
	}

	public boolean isScroll() {
		return scrolling;
	}

}
