import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class TileCreateTest {

	@Test
	public void tilecreate() {
		
		List <Tiles> tile = new LinkedList<>();
		TileCreate t = new TileCreate();
		t.tileCreate(tile);
		
		assertEquals(5,tile.size());
	}
	
	@Test
	public void tileupdate() {
		
		List <Tiles> tile = new LinkedList<>();
		
		Tiles tiles = new Tiles(30,30);
		tile.add(tiles);
		TileCreate t = new TileCreate();
		t.tileCreate(tile);
		
		assertEquals(5,tile.size());
		
		t.finalCreate(tile);
		
		assertEquals(9,tile.size());
	}
	
}
