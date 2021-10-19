package otus.orm.exp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.orm.exp.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends MongoRepository<Book, String> {

    Optional<Book> findById(String id);

    void deleteById(String id);

}
