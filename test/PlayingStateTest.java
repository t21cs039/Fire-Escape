import static org.junit.Assert.*;

import org.junit.Test;

public class PlayingStateTest {

	@Test
	public void 生成できる() {
		Model m = new Model();
		m.processKeyTyped("ENTER");
		State state = m.getState();
		assertTrue(state instanceof PlayingState);
		
		m.processTimeElapsed(100);
		state = m.getState();
		assertTrue(state instanceof PlayingState);
		
		m.processKeyTyped("w");
		state = m.getState();
		assertTrue(state instanceof PlayingState);
		
		m.processKeyTyped(" ");
		state = m.getState();
		assertTrue(state instanceof BossState);
		
		m.processKeyTyped("w");
		state = m.getState();
		assertTrue(state instanceof BossState);

	}

}
