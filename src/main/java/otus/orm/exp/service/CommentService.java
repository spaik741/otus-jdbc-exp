package otus.orm.exp.service;

import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Comment;

import java.util.List;
import java.util.Optional;


public interface CommentService {
    List<Comment> getAllComments(String idBook);

    Optional<Comment> getCommentById(String id);

    void deleteComment(String id);

    void deleteCommentByBookId(String idBook);

    Optional<Comment> saveComment(Comment comment);
}
