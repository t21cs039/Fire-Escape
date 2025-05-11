import java.awt.*;

public class Fire extends Objects {
	
	private int height;
	private int n=1;
	private Image image;
	
	public Fire(int x, int y, int h) {
		super();
		this.x = x;
		this.y = y;
		this.height = h;
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("fire.png"));
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, x, y, Game.WIN_WIDTH,Model.SCALE+height, null);
	}

	//火を上下に動く
	public void firemove() {
		n++;
		if(n%2==0) {
			y-=2;
			height+=2;
		}else {
			y+=2;
			height-=2;
		}
		
	}
	public void update() {
		y-=10;
		height+=10;
	}
	
	public void scroll() {
		//下に移動する
		if(y<400) {
			y+=10;
			height-=10;
		}
		
	}

}
