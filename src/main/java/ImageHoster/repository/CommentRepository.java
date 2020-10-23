package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comment addComment(Comment comment)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        try
        {
            et.begin();
            em.persist(comment);
            et.commit();
        }
        catch (Exception e)
        {
            et.rollback();
        }

        return comment;
    }

    public List<Comment> getAllComments(Image image)
    {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> q = em.createQuery("SELECT i from Comment i where i.image.id = :image_id",Comment.class).setParameter("image_id",image.getId() );
        return  q.getResultList();

    }
}
