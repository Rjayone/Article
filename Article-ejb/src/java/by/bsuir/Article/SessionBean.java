package by.bsuir.Article;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
@LocalBean
public class SessionBean implements SessionBeanLocal {
    private EntityManager em = null;

    public SessionBean() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Article-ejbPU");
        em = emf.createEntityManager();
    }
    
    @Override
    public void addArticle(String author, String text) {
        em.getTransaction();
        Article article = new Article();
        article.setAuthor(author);
        article.setText(text);
        Article articleDB = em.merge(article);
        em.getTransaction().commit();
    }    

    @Override
    public void removeArticle(String id) {
        em.getTransaction().begin();
        em.remove(em.find(Article.class, Integer.parseInt(id)));
        em.getTransaction().commit();
    }

    @Override
    public void editArticle(String id, String author, String text) {

        em.getTransaction().begin();
        Article article = new Article();
        article.setAuthor(author);
        article.setText(text);
        article.setIdArticle(Integer.parseInt(id));
        em.merge(article);
        em.getTransaction().commit();
    }
}
