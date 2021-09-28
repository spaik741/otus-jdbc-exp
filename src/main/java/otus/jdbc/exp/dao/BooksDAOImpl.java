package otus.jdbc.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Repository;
import otus.jdbc.exp.entity.Book;

import javax.persistence.*;
import java.util.List;

@Repository
public class BooksDAOImpl implements BooksDAO {

    @PersistenceContext
    private final EntityManager em;

    public BooksDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Book findById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("library-books-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        return BooleanUtils.toBoolean(query.executeUpdate());

    }
}
