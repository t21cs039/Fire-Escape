import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileTest {
	
	@Test
	public void tiletest() {
	Tiles t = new Tiles(30,30);
	
	assertEquals(30,t.getX());
	assertEquals(30,t.getY());
	
	}
	
	
}
