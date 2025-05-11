import static org.junit.Assert.*;

import org.junit.Test;

public class FireBallTest {

	@Test
	public void test() {
		FireBall f = new FireBall(2,4,5,0);
		assertEquals(2,f.getX());
		assertEquals(4,f.getY());
		
		f.update();
		
		assertEquals(7,f.getX());
		assertEquals(4,f.getY());
	}

}
