package otus.orm.exp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.orm.exp.entity.Author;

import java.util.List;

public interface AuthorsRepository extends MongoRepository<Author, String> {

}
