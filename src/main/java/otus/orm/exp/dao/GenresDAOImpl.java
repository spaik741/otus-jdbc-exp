package otus.orm.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Repository;
import otus.orm.exp.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GenresDAOImpl implements GenresDAO {

    @PersistenceContext
    private final EntityManager em;

    public GenresDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Genre findById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> findAll() {
        Query query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        Query query = em.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        return BooleanUtils.toBoolean(query.executeUpdate());
    }

}
