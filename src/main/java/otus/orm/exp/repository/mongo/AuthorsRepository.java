package otus.orm.exp.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.orm.exp.entity.mongo.Author;

public interface AuthorsRepository extends MongoRepository<Author, String> {

}
