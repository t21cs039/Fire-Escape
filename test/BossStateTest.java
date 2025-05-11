import static org.junit.Assert.*;
import org.junit.Test;

public class BossStateTest {

	private BossState bs;
	private Model m;
	private PlayingState ps;
	@Test
	public void コンストラクタできる() {
		
		m = new Model();
		ps = new PlayingState(m);
		bs = new BossState(ps, m);
		
		assertEquals(0, bs.cursorValue);
		assertEquals(200, bs.pointer);
		assertEquals(bs, bs.processTimeElapsed(100));
		
	}
	
	@Test
	public void スペースキーでステート変わる() {
		m = new Model();
		m.processKeyTyped("ENTER");
		ps = (PlayingState) m.getState();
		
		m.processKeyTyped(" ");
		State s = m.getState();
		
		assertEquals(BossState.class, s.getClass());
		m.processKeyTyped(" ");
		s = m.getState();
		assertNotEquals(BossState.class, s.getClass());
		assertEquals(PlayingState.class, s.getClass());

	}
	
	public void カーソルを上に移動できる() {
		m = new Model();
		ps = new PlayingState(m);
		bs = new BossState(ps, m);
		
		bs.cursorValue = 1;
        bs.processKeyTyped("w");
        assertEquals(0, bs.cursorValue);
	}
	
	public void カーソルを下に移動できる() {
		m = new Model();
		ps = new PlayingState(m);
		bs = new BossState(ps, m);
		
		bs.cursorValue = 1;
        bs.processKeyTyped("s");
        assertEquals(2, bs.cursorValue);
	}
	
	public void 遊び方を選択できる() {
		ps = new PlayingState(m);
		bs = new BossState(ps, m);
		bs.cursorValue = BossState.INSTRUCTION;
		Menu m = new Menu("Instruction.txt", bs);
        assertEquals(m, bs.processKeyTyped("ENTER"));
        
	}
	public void 再始動できる() {
		m = new Model();
		ps = new PlayingState(m);
		bs = new BossState(ps, m);
		
		bs.cursorValue = BossState.RESTART;
		TitleState t = new TitleState(m);
        assertEquals(t,  bs.processKeyTyped("ENTER"));
        
        bs.processTimeElapsed(1000);
        
	}
	

}
