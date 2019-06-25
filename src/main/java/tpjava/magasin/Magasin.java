package tpjava.magasin;

import tpjava.bo.Article;
import tpjava.bo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe qui met en place la logique de fonctionnement d'un magasin, en utilisant les Classes
 * Article, User et Cart.
 *
 * @author Glen OLLIVIER
 */
public class Magasin {
    private Map<User, Cart> users;
    private Map<Article, Integer> articles;

    public Magasin() {
        users = new HashMap<>();
        articles = new HashMap<>();
    }

    /**
     * Méthode qui ajoute un nouvel utilisateur au magasin.
     * Si un utilisateur identique existe déja (en se basant sue le equals de User), cette
     * méthode se contente de renvoyer null.
     *
     * @param user l'utilisateur à ajouter.
     * @return l'utilisateur s'il n'étais pas déja dans de Magasin, null sinon.
     */
    public User addUser(User user) {
        if (users.containsKey(user)) {
            user = null;
        } else {
            users.put(user, new Cart(this));
        }
        return user;
    }

    /**
     * Méthode qui ajoute un nouvel article au stock du magasin, ou qui augmente le stock d'un
     * Article existant.
     *
     * @param article L'article à ajouter/restocker.
     * @param nb      Le nombre de cet article à ajouter.
     * @return Le nombre total de cet article en stock au magasin après cette opération.
     */
    public Integer addArticle(Article article, Integer nb) {
        if (articles.containsKey(article)) {
            nb += articles.get(article);
        }
        articles.put(article, nb);
        return nb;
    }

    /**
     * Méthode qui renvoie la quantité en stock d'un article donné.
     *
     * @param article L'article dont on veux connaître le stock.
     * @return La quantité de cet article en stock (0 si l'article n'est pasn dans le magasin).
     */
    public Integer getStock(Article article) {
        int nb = 0;
        if (articles.containsKey(article)) {
            nb += articles.get(article);
        }
        return nb;
    }

    /**
     * Méthode qui permet d'acheter une quantité donnée d'un article donné.
     *
     * @param article L'article q'on veux acheter.
     * @param nb      La quantité à acheter.
     * @return true si l'achat a bien été effectué, false sinon (stock insuffisant, ...).
     */
    public boolean buy(Article article, Integer nb) {
        boolean bought = false;
        if (articles.containsKey(article) && articles.get(article) >= nb) {
            articles.put(article, articles.get(article) - nb);
            bought = true;
        }
        return bought;
    }

    /**
     * Méthode qui permet d'ajouter un article au panier d'un utilisateur.
     *
     * @param user    L'utilisateur à qui appartient le panier dans lequel on veux ajouter l'article.
     * @param article L'article à ajouter.
     * @param nb      La quantité de l'article à ajouter.
     * @return true si l'objet a bien été ajouté, false sinon (stock insuffisant, ...).
     */
    public boolean addToCart(User user, Article article, Integer nb) {
        boolean added = false;
        if (users.containsKey(user) && nb > 0) {
            if (users.get(user).add(article, nb) >= nb) {
                added = true;
            }
        }
        return added;
    }

    /**
     * Méthode qui permet d'acheter au magasin le contenu du panier d'un utilisateur
     * donné. Le panier est ensuite vidé si l'opération est un succès.
     *
     * @param user L'utilisateur qui veux acheter le contenu de son panier.
     * @return true si le panier a bien été acheté, false sinon (stock insuffisant, ...).
     */
    public boolean checkout(User user) {
        boolean bought = false;
        if (users.containsKey(user)) {
            if (users.get(user).checkout()) {
                bought = true;
            }
        }
        return bought;
    }

    /**
     * Méthode qui vide le panier d'un utilisateur donné sans achat.
     */
    public boolean empty(User user) {
        boolean emptied = false;
        if (users.containsKey(user)) {
            users.get(user).empty();
            emptied = true;
        }
        return emptied;
    }
}
