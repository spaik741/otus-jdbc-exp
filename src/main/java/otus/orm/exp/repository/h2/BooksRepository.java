package otus.orm.exp.repository.h2;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.h2.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Long> {

    @EntityGraph("BookGraph")
    Optional<Book> findById(long id);
    @Override
    @EntityGraph("BookGraph")
    List<Book> findAll();

    void deleteById(long id);

}
