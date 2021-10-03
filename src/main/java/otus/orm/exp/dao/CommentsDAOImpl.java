package otus.orm.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Repository;
import otus.orm.exp.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CommentsDAOImpl implements CommentsDAO{

    @PersistenceContext
    private final EntityManager em;

    public CommentsDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Comment findById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> findAll() {
        Query query = em.createQuery("select c from Comment c join fetch c.book", Comment.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        Query query = em.createQuery("delete from Comment с where с.id = :id");
        query.setParameter("id", id);
        return BooleanUtils.toBoolean(query.executeUpdate());
    }
}
