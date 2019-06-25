package tpjava.magasin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tpjava.bo.Article;
import tpjava.bo.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MagasinTest {
    Magasin m;

    public static final Article CHIPS = new Article("chips");
    public static final Article COCA = new Article("coca");
    public static final Article DORITOS = new Article("doritos");
    public static final Article CAROTTE = new Article("carotte");
    public static final Article PATATE = new Article("patate");
    public static final Article EAU = new Article("eau");
    public static final Article VODKA = new Article("vodka");
    public static final Article WHISKY = new Article("whisky");

    public static final User BOB = new User("bob", "bob@keitvimpbev.bzh", LocalDate.of(1995,9,2));
    public static final User FANCH = new User("fañch", "fanch.mitt@ps.gouv.fr", LocalDate.of(1916,10,26));
    public static final User YANNIG = new User("yannig", "yann.koadou@nanarland.org", LocalDate.of(1993,6,12));

    @BeforeEach
    void setUp() {
        m = new Magasin();
        m.addUser(BOB);
        m.addUser(FANCH);
        m.addUser(YANNIG);
        m.addArticle(CHIPS, 30);
        m.addArticle(COCA, 15);
        m.addArticle(DORITOS, 5);
        m.addArticle(CAROTTE, 8);
        m.addArticle(PATATE, 50);
        m.addArticle(EAU, 10);
        m.addArticle(VODKA, 3);
        m.addToCart(BOB, CHIPS, 3);
        m.addToCart(BOB, CAROTTE, 3);
        m.addToCart(BOB, PATATE, 5);
        m.addToCart(BOB, VODKA, 1);
        m.addToCart(BOB, EAU, 1);
        m.addToCart(FANCH, COCA, 2);
        m.addToCart(FANCH, DORITOS, 3);
        m.addToCart(FANCH, PATATE, 5);
        m.addToCart(FANCH, VODKA, 1);
        m.addToCart(FANCH, EAU, 3);
    }

    @Test
    void addUser() {
        assertEquals(null, m.addUser(new User("bob", "bob@keitvimpbev.bzh", LocalDate.now())));
        assertEquals(null, m.addUser(new User("albert", "bob@keitvimpbev.bzh", LocalDate.now())));
        assertNotEquals(null, m.addUser(new User("bob", "bob@yahoo.com", LocalDate.now())));
        assertEquals(null, m.addUser(new User("bob", "bob@yahoo.com", LocalDate.now())));
    }

    @Test
    void addArticle() {
        assertEquals(50, m.addArticle(CHIPS, 20).intValue());
        assertEquals(50, m.getStock(CHIPS).intValue());
        assertEquals(0, m.getStock(WHISKY).intValue());
        assertEquals(20, m.addArticle(WHISKY, 20).intValue());
        assertEquals(20, m.getStock(WHISKY).intValue());
    }

    @Test
    void buy() {
        assertTrue(m.buy(CHIPS, 10));
        assertEquals(20, m.getStock(CHIPS).intValue());
        assertFalse(m.buy(WHISKY, 10));
        assertEquals(0, m.getStock(WHISKY).intValue());
        assertFalse(m.buy(CHIPS, 21));
        assertEquals(20, m.getStock(CHIPS).intValue());
        assertTrue(m.buy(CHIPS, 20));
        assertEquals(0, m.getStock(CHIPS).intValue());
    }

    @Test
    void addToCart() {
        assertTrue(m.addToCart(YANNIG, CHIPS, 10));
        assertTrue(m.addToCart(YANNIG, CHIPS, 20));
        assertFalse(m.addToCart(YANNIG, CHIPS, 1));
        m.empty(YANNIG);
        m.buy(CHIPS, 25);
        assertFalse(m.addToCart(YANNIG, CHIPS, 10));
        assertTrue(m.addToCart(YANNIG, CHIPS, 5));
    }

    @Test
    void checkout() {
        assertTrue(m.addToCart(YANNIG, CHIPS, 30));
        assertTrue(m.checkout(BOB));
        assertTrue(m.checkout(FANCH));
        assertFalse(m.checkout(YANNIG));
        m.empty(YANNIG);
        m.addToCart(YANNIG, CHIPS, 27);
        assertTrue(m.checkout(YANNIG));
    }
}