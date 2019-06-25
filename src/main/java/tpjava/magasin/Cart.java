package tpjava.magasin;

import tpjava.bo.Article;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Article, Integer> articles;
    private Magasin magasin;

    public Cart(Magasin magasin) {
        this.magasin = magasin;
        articles = new HashMap<>();
    }

    public Integer add(Article article, Integer nb) {
        int oldNb = 0;
        if (articles.containsKey(article)) {
            oldNb += articles.get(article);
        }
        if (magasin.getStock(article) < nb + oldNb) {
            nb = 0;
        } else {
            articles.put(article, nb + oldNb);
        }
        return nb;
    }


    public boolean checkout() {
        boolean isBuyable = true;
        for(Map.Entry<Article, Integer> entry : articles.entrySet()) {
            if(magasin.getStock(entry.getKey()) < entry.getValue()) {
                isBuyable = false;
            }
        }
        if (isBuyable) {
            for(Map.Entry<Article, Integer> entry : articles.entrySet()) {
                magasin.buy(entry.getKey(), entry.getValue());
            }
            articles.clear();
        }
        return isBuyable;
    }

    public void empty() {
        articles.clear();
    }
}
