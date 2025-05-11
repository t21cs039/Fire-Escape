import java.awt.*;

public class Tiles {
	
	private Image image =  Toolkit.getDefaultToolkit().getImage(getClass().getResource("tile.gif")) ;
	private int y ;
	private int x;
	
	public Tiles(int x , int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void paintComponent(Graphics g) {
		//タイルごとに3個持つ
		g.drawImage(image, x, y, Model.SCALE,Model.SCALE/2,null);
		g.drawImage(image, x+Model.SCALE, y, Model.SCALE,Model.SCALE/2,null);
		g.drawImage(image, x+Model.SCALE+Model.SCALE, y, Model.SCALE,Model.SCALE/2,null);
	}

	public int getX() {
		return x;
	}
	
	public int getWidth() {
		return Model.SCALE*3;
	}
	
	public int getHeight() {
		return Model.SCALE/2;
	}
	

}
