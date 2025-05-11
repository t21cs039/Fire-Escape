import java.awt.Graphics;

public class Goal extends Objects {
	
	public Goal () {
		x=300;
		y=50;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(goal, x, y, Model.SCALE, Model.SCALE, null);
	}

	public boolean arrivedGoal(Player player) {
		return( (player.getX()> x-10) && (player.getX()<x+10) && (player.getY()<100) && (player.getY()>y-10));
	}

}
