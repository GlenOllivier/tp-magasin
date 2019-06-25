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
        User bob = m.addUser("bob", "bob@keitvimpbev.bzh", LocalDate.of(1995,9,2));
        User fanch = m.addUser("fañch", "fanch.mitt@ps.gouv.fr", LocalDate.of(1916,10,26));
        User yannig = m.addUser("yannig", "yann.koadou@nanarland.org", LocalDate.of(1993,6,12));
        m.addArticle(CHIPS, 30);
        m.addArticle(COCA, 15);
        m.addArticle(DORITOS, 5);
        m.addArticle(CAROTTE, 8);
        m.addArticle(PATATE, 50);
        m.addArticle(EAU, 10);
        m.addArticle(VODKA, 3);
        m.addToCart(bob, CHIPS, 3);
        m.addToCart(bob, CAROTTE, 3);
        m.addToCart(bob, PATATE, 5);
        m.addToCart(bob, VODKA, 1);
        m.addToCart(bob, EAU, 1);
        m.addToCart(fanch, COCA, 2);
        m.addToCart(fanch, DORITOS, 3);
        m.addToCart(fanch, PATATE, 5);
        m.addToCart(fanch, VODKA, 1);
        m.addToCart(fanch, EAU, 3);
    }

    @Test
    void addUser() {
        assertEquals(null, m.addUser("bob", "bob@keitvimpbev.bzh", LocalDate.now()));
        assertEquals(null, m.addUser("albert", "bob@keitvimpbev.bzh", LocalDate.now()));
        assertNotEquals(null, m.addUser("bob", "bob@yahoo.com", LocalDate.now()));
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
    }

    @Test
    void addToCart() {
    }

    @Test
    void checkout() {
    }
}