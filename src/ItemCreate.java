import java.util.List;
import java.util.ListIterator;

public class ItemCreate {

	RandNumGenerator r;
	
	public void createCoin(List<Coin> coins, List<Tiles> tile) {
		int max = 150;
		int min = 0;
		int y = 50;
		r = RandNumGenerator.getInstance();
		
		// Get a list iterator starting from the end (reverse order)
        ListIterator<Tiles> iterator = tile.listIterator(tile.size());

        while (iterator.hasPrevious()) {
            Tiles t = iterator.previous();
			int x = t.getX() + r.nextInt(max+min-1)+min;
			if(coins.size()<2 ) {
				Coin c = new Coin(x, y);
				coins.add(c);
				y+=100;
			}
		}
        
	}

	public void createStar(List<Coin> coins, List<Star> stars, int time) {
		int max = 50;
		int min = 0;
		int x = 150;
		int y = 150;
		r = RandNumGenerator.getInstance();
		
		if(time%100==0 && stars.isEmpty()) {
			x+= r.nextInt(max+min-1)+min;
			for(Coin c:coins) {
				if(x==c.getX()) {//他のアイテムの座標と同じであれば
					x=150;
					x+= r.nextInt(max+min-1)+min;//新し座標を作成する
				}		
			}
			Star s = new Star(x,y);
			stars.add(s);
		}
		
	}
	
}
