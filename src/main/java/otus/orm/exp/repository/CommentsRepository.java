package otus.orm.exp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.Comment;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBookId(long idBook);

}
