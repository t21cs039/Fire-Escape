import static org.junit.Assert.*;

import org.junit.Test;

public class ResultStateTest {

    @Test
    public void メニュを指すことができる() {
        Model m = new Model();
        Result rs = new Result();
        Score s = new Score();
        ResultState r = new ResultState(m, rs, s);

        r.processKeyTyped("d");

        assertEquals(1, r.cursorValue);
        assertEquals(350, r.pointer);

        r.processKeyTyped("a");

        assertEquals(0, r.cursorValue);
        assertEquals(50, r.pointer);
        
    }

}
