import static org.junit.Assert.*;
import org.junit.Test;

public class ScoreTest {

	@Test
	public void スコア上げる() {
		Score s = new Score();
		s.add();
		assertEquals(20,s.getScore());
		
		s.setTime(10000);
		assertEquals(120,s.getScore());
		
	}

}
