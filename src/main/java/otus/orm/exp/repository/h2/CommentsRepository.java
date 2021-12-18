package otus.orm.exp.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.h2.CommentTo;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentTo, Long> {

    List<CommentTo> findAllByBookToId(long idBook);

}
