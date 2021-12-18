package otus.orm.exp.repository.h2;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.h2.BookTo;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<BookTo, Long> {

    @EntityGraph("BookGraph")
    Optional<BookTo> findById(long id);
    @Override
    @EntityGraph("BookGraph")
    List<BookTo> findAll();

    void deleteById(long id);

}
