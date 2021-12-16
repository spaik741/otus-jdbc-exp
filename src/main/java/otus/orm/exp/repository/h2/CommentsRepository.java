package otus.orm.exp.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.h2.Comment;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBookId(long idBook);

}
