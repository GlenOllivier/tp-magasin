package tpjava.magasin;

import tpjava.bo.Article;
import tpjava.bo.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class Magasin {
    private Map<User, Cart> users;
    private Map<Article, Integer> articles;

    public Magasin () {
        users = new HashMap<>();
        articles = new HashMap<>();
    }

    public User addUser(String name, String email, LocalDate birthDate) {
        User user = new User(name, email, birthDate);
        if (users.containsKey(user)) {
            user = null;
        } else {
            users.put(user, new Cart(this));
        }
        return user;
    }

    public Integer addArticle(Article article, Integer nb) {
        if (articles.containsKey(article)) {
            nb += articles.get(article);
        }
        articles.put(article, nb);
        return nb;
    }

    public Integer getStock(Article article) {
        int nb = 0;
        if (articles.containsKey(article)) {
            nb += articles.get(article);
        }
        return nb;
    }

    public boolean buy (Article article, Integer nb) {
        boolean bought = false;
        if (articles.containsKey(article) && articles.get(article) >= nb) {
            articles.put(article, articles.get(article) - nb);
            bought = true;
        }
        return bought;
    }

    public boolean addToCart(User user, Article article, Integer nb) {
        boolean added = false;
        if (users.containsKey(user) && nb > 0) {
            if (users.get(user).add(article, nb) >= nb) {
                added = true;
            }
        }
        return added;
    }

    public boolean checkout (User user) {
        boolean bought = false;
        if (users.containsKey(user)) {
            if (users.get(user).checkout()) {
                bought = true;
            }
        }
        return bought;
    }

    public boolean empty(User user) {
        boolean emptied = false;
        if (users.containsKey(user)) {
            users.get(user).empty();
            emptied = true;
        }
        return emptied;
    }
}
