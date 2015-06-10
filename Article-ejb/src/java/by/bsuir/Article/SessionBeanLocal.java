package by.bsuir.Article;

import javax.ejb.Local;


@Local
public interface SessionBeanLocal {

    void addArticle(String author, String text);

    void removeArticle(String id);

    void editArticle(String id, String author, String text);
    
}
