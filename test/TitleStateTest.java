import static org.junit.Assert.*;

import org.junit.Test;

public class TitleStateTest {

    @Test
    public void タイトルのインスタンスが修得できる() {
    	Model m = new Model();
        TitleState t = new TitleState(m);
        assertNotNull(t);
    }

    @Test
    public void メニュを指すことができる() {
    	
    	Model m = new Model();
        TitleState t = new TitleState(m);

        assertEquals(0, t.cursorValue);
        
        t.processKeyTyped("s");
        assertEquals(1, t.cursorValue);
        assertEquals(250, t.pointer);

        t.processKeyTyped("w");
        assertEquals(0, t.cursorValue);
        assertEquals(200, t.pointer);
        
        t.processKeyTyped("w");
        assertEquals(0, t.cursorValue);
        assertEquals(200, t.pointer);
        
        int i = 0;
        while(i<5){
        	t.processKeyTyped("s");
        	i++;
        }
        assertEquals(3, t.cursorValue);
        assertEquals(350, t.pointer);
        
        t = (TitleState) m.getState();
        assertEquals(t, m.getState());
        
        m.processKeyTyped("s");
        m.processKeyTyped("ENTER");
        Menu mn = (Menu) m.getState();
        
        assertNotEquals(t, m.getState());
        assertEquals(mn, m.getState());
        
        m.processKeyTyped("ENTER");
        assertEquals(t, m.getState());
    }

    @Test
    public void 難易度を選択できる() {
    	Model m = new Model();
        TitleState t = new TitleState(m);
        Difficulty d = Difficulty.getInstance();

        t.processKeyTyped("d");
        assertEquals(1, d.getMode());
        t.processKeyTyped("d");
        assertEquals(2, d.getMode());

        t.processKeyTyped("a");
        assertEquals(1, d.getMode());
        t.processKeyTyped("a");
        assertEquals(0, d.getMode());
        
        t.processKeyTyped("s");
        t.processKeyTyped("a");
        assertEquals(0, d.getMode());
        t.processKeyTyped("d");
        assertEquals(0, d.getMode());
        
        assertEquals(0, d.getEasy());
        assertEquals(1, d.getNormal());
        assertEquals(2, d.getHard());
        
        assertEquals(30, d.getTime());
        d.increase();
        assertEquals(20, d.getTime());
        d.increase();
        assertEquals(10, d.getTime());
    }

}
