package otus.orm.exp.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.Book;

import java.util.List;

public interface BooksDAO extends JpaRepository<Book, Long> {

    @EntityGraph("BookGraph")
    Book findById(long id);
    @Override
    @EntityGraph("BookGraph")
    List<Book> findAll();

    void deleteById(long id);

}
