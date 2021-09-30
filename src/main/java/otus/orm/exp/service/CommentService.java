package otus.orm.exp.service;

import otus.orm.exp.entity.Comment;

import java.util.List;
import java.util.Optional;


public interface CommentService {
    List<Comment> getAllComments();

    Optional<Comment> getCommentById(long id);

    boolean deleteComment(long id);

    boolean saveComment(Comment comment);
}
