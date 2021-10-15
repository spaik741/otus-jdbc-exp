package otus.orm.exp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.orm.exp.entity.Comment;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String idBook);

    void deleteByBookId(String idBook);
}
