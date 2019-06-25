package tpjava.magasin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tpjava.bo.Article;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    Magasin m;
    Cart c, c2;

    @BeforeEach
    void setUp() {
        m = new Magasin();
        m.addArticle(new Article("chips"), 30);
        m.addArticle(new Article("coca"), 15);
        m.addArticle(new Article("doritos"), 5);
        m.addArticle(new Article("carotte"), 8);
        m.addArticle(new Article("patate"), 50);
        m.addArticle(new Article("eau"), 10);
        m.addArticle(new Article("vodka"), 3);
        c = new Cart(m);
        c2 = new Cart(m);
    }


    @Test
    void add() {
        assertEquals(25, c.add(new Article("chips"), 25).intValue());
        assertEquals(8, c.add(new Article("carotte"), 8).intValue());
        assertEquals(0, c.add(new Article("chips"), 25).intValue());
        assertEquals(0, c.add(new Article("burger"), 25).intValue());
    }

    @Test
    void checkout() {
        c.add(new Article("chips"), 25);
        c.add(new Article("carotte"), 8);
        c.add(new Article("vodka"), 1);
        c2.add(new Article("chips"), 25);
        c2.add(new Article("carotte"), 8);
        c2.add(new Article("vodka"), 1);
        assertEquals(true, c2.checkout());
        assertEquals(5, m.getStock(new Article("chips")).intValue());
        assertEquals(0, m.getStock(new Article("carotte")).intValue());
        assertEquals(2, m.getStock(new Article("vodka")).intValue());
        assertEquals(true, c2.checkout());
        assertEquals(5, m.getStock(new Article("chips")).intValue());
        assertEquals(0, m.getStock(new Article("carotte")).intValue());
        assertEquals(2, m.getStock(new Article("vodka")).intValue());
        assertEquals(false, c.checkout());
        assertEquals(5, m.getStock(new Article("chips")).intValue());
        assertEquals(0, m.getStock(new Article("carotte")).intValue());
        assertEquals(2, m.getStock(new Article("vodka")).intValue());
        c.empty();
        c.add(new Article("vodka"), 1);
        assertEquals(true, c.checkout());
        assertEquals(5, m.getStock(new Article("chips")).intValue());
        assertEquals(0, m.getStock(new Article("carotte")).intValue());
        assertEquals(1, m.getStock(new Article("vodka")).intValue());
    }
}