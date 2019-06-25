package tpjava.magasin;

import tpjava.bo.Article;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe qui gère les panniers des utilisateurs.
 * Elle contient la logique de manipulation des paniers.
 *
 * @author Glen OLLIVIER
 */
public class Cart {
    private Map<Article, Integer> articles;
    private Magasin magasin;

    public Cart(Magasin magasin) {
        this.magasin = magasin;
        articles = new HashMap<>();
    }

    /**
     * Méthode qui permet d'ajouter un article au panier. Cette méthode considère que l'utilisateur
     * n'est pas VIP.
     *
     * @param article l'article à ajouter.
     * @param nb      la quantité à ajouter.
     * @return la quantité d'articles ajoutés. Renvoie 0 si le stock du magasin est insuffisant.
     */
    public Integer add(Article article, Integer nb) {
        return add(article, nb, false);
    }

    /**
     * Méthode qui permet d'ajouter un article au panier. Cette méthode demande si l'utilisateur
     * est VIP.
     *
     * @param article l'article à ajouter.
     * @param nb      la quantité à ajouter.
     * @param isVip   le statut de l'utilisateur.
     * @return la quantité d'articles ajoutés. Renvoie 0 si le stock du magasin est insuffisant.
     */
    public Integer add(Article article, Integer nb, boolean isVip) {
        int oldNb = 0;
        if (articles.containsKey(article)) {
            oldNb += articles.get(article);
        }
        if (magasin.getStock(article, isVip) < nb + oldNb) {
            nb = 0;
        } else {
            articles.put(article, nb + oldNb);
        }
        return nb;
    }

    /**
     * Méthode qui permet d'acheter au magasin le contenu du panier. Le panier est ensuite
     * vidé si l'opération est un succès. Cette méthode considère que l'utilisateur n'est
     * pas VIP.
     *
     * @return un booléen qui renvoie true si l'opération s'est bien passée, false sinon
     * (stock insuffisant, ...).
     */
    public boolean checkout() {
        return checkout(false);
    }

    /**
     * Méthode qui permet d'acheter au magasin le contenu du panier. Le panier est ensuite
     * vidé si l'opération est un succès. Cette méthode demande si l'utilisateur est VIP.
     *
     * @param isVip le statut de l'utilisateur.
     * @return un booléen qui renvoie true si l'opération s'est bien passée, false sinon
     * (stock insuffisant, ...).
     */
    public boolean checkout(boolean isVip) {
        boolean isBuyable = true;
        for (Map.Entry<Article, Integer> entry : articles.entrySet()) {
            if (magasin.getStock(entry.getKey(), isVip) < entry.getValue()) {
                isBuyable = false;
            }
        }
        if (isBuyable) {
            for (Map.Entry<Article, Integer> entry : articles.entrySet()) {
                magasin.buy(entry.getKey(), entry.getValue(), isVip);
            }
            articles.clear();
        }
        return isBuyable;
    }

    /**
     * Méthode qui vide le panier sans acheter.
     */
    public void empty() {
        articles.clear();
    }
}
