package otus.jdbc.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
