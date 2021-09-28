package otus.jdbc.exp.dao;

import otus.jdbc.exp.entity.Comment;

import java.util.List;

public interface CommentsDAO {
    Comment save(Comment comment);

    Comment findById(long id);

    List<Comment> findAll();

    boolean deleteById(long id);
}
