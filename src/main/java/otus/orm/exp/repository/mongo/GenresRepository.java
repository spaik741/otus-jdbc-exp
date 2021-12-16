package otus.orm.exp.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.orm.exp.entity.mongo.Genre;

public interface GenresRepository extends MongoRepository<Genre, String> {

}
