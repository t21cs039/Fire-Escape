import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void プレイヤーの座標を設定できる() {
        Player p = new Player(20, 20);
        assertEquals(20, p.getX());
        assertEquals(20, p.getY());
    }

    @Test
    public void プレイヤーが左右に移動できる() {
        Player p = new Player(20, 20);

        p.playerMove("a");
        assertEquals(16, p.getX());
        assertEquals(20, p.getY());

        p.playerMove("d");
        assertEquals(20, p.getX());
        assertEquals(20, p.getY());
        
        p.setX(550);
        p.playerMove("d");
        assertEquals(550, p.getX());
        
        p.setX(1);
        p.playerMove("a");
        assertEquals(1,p.getX());
    }

    @Test
    public void プレイヤーがプラットフォームから落とすことができる() {
        Player p = new Player(20, 0);

        assertFalse(p.isFall());
        assertFalse(p.isJump());

        Tiles t = new Tiles(10, 200);
        LinkedList<Tiles> tile = new LinkedList<>();
        tile.add(t);
        p.check(tile);

        assertTrue(p.isFall());
        assertFalse(p.isJump());
        
        int n = 0;
        while (n < 500) {
            p.check(tile);
            n++;
        }

        assertFalse(p.isFall());
        
        p = new Player(20, 0);
        t = new Tiles(200, 200);
        tile = new LinkedList<>();
        tile.add(t);

        n = 0;
        while (n < 60) {
            p.check(tile);
            n++;
        }

        assertTrue(p.isFall());
    }

    @Test
    public void プレイヤーがジャンプできる() {
        Player p = new Player(20, 150);

        Tiles t = new Tiles(10, 200);
        LinkedList<Tiles> tile = new LinkedList<>();
        tile.add(t);

        assertFalse(p.isJump());

        while (p.isFall()) {
            p.check(tile);
        }

        assertFalse(p.isFall());
        assertFalse(p.isJump());

        p.playerMove("w");
        assertTrue(p.isJump());
       

        int n = 0;
        while (n < 20) {
            p.check(tile);
            n++;
        }

        assertFalse(p.isJump());
        assertFalse(p.isFall());
        
        p.setJump(true);
        p.isJumping();
        assertEquals(120, p.getY());
        
    }

    @Test
    public void スクロールするときプレイヤーの座標を更新できる() {
        Player p = new Player(100, 100);

        p.scroll(30);

        assertEquals(100, p.getX());
        assertEquals(130, p.getY());
    }
    
    
    @Test
    public void アイテムの取得() {
    	
    	Coin cc = new Coin(20,20);
    	Star s = new Star(20,20);
    	Player p = new Player(20, 20);
    	LinkedList<Tiles> tile = new LinkedList<>();
    	
    	p.check(tile);
    	assertTrue(p.getCoin(cc));
    	assertTrue(p.getStar(s));
    	
    	FireBall f = new FireBall(10,20,5,0);
    	f.update();
    	assertTrue(p.isHit(f));
    	
    	f = new FireBall(30,20,-5,0);
    	f.update();
    	assertTrue(p.isHit(f));
    }
    
}
