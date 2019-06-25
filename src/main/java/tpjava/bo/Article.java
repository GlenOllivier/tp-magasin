package tpjava.bo;

import java.util.Objects;

public class Article {
    private String name;

    public Article(String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(name, article.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
