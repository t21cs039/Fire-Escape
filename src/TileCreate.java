import java.util.List;

public class TileCreate {
	
	private List <Tiles> tile;
	private int n;
	
	//タイルをランダムのx座標に生成する
	public void tileCreate(List<Tiles> tiles) {
		this.tile=tiles;
		int y=0;
		int max = 100;
		int min = 30;
		RandNumGenerator r = RandNumGenerator.getInstance();
		
		//4個タイルを作成する
		while(tile.size()<5) {
			
			//タイルは左と右、交互に生成する
			if(n>250)
				n-=r.nextInt(max - 1 + min)+ min;
			else
				n+=r.nextInt(max - 1 + min)+ min;
			
			Tiles t = new Tiles(n, y);
			tile.add(t);
			y+=100;
		}
	}
	
	//最後のステージを作る（ゴール）
	public void finalCreate(List<Tiles> tiles) {
		this.tile=tiles;
		Tiles t1 = new Tiles(0,0);
		tile.add(t1);
		Tiles t2 = new Tiles(150,0);
		tile.add(t2);
		Tiles t3 = new Tiles(300,0);
		tile.add(t3);
		Tiles t4 = new Tiles(450,0);
		tile.add(t4);
	}
	
	public List<Tiles> getTile(){
		return tile;
	}
	

}
