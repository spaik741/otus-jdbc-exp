package otus.orm.exp.service;

import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Comment;

import java.util.List;
import java.util.Optional;


public interface CommentService {
    List<Comment> getAllComments(long idBook);

    Optional<Comment> getCommentById(long id);

    void deleteComment(long id);

    Optional<Comment> saveComment(Comment comment);
}
