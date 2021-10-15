package otus.orm.exp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.orm.exp.entity.Genre;

import java.util.List;

public interface GenresRepository extends MongoRepository<Genre, String> {

}
