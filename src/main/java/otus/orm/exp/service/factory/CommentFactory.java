package otus.orm.exp.service.factory;

import otus.orm.exp.entity.Comment;

import java.util.Date;
import java.util.Optional;

public interface CommentFactory {
    Optional<Comment> createComment(String text, Date date, String idBook);
}
