package otus.orm.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Repository;
import otus.orm.exp.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AuthorsDAOImpl implements AuthorsDAO {

    @PersistenceContext
    private final EntityManager em;

    public AuthorsDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Author findById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> findAll() {
        Query query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        Query query = em.createQuery("delete from Author a where a.id = :id");
        query.setParameter("id", id);
        return BooleanUtils.toBoolean(query.executeUpdate());
    }

}
