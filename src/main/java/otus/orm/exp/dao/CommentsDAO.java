package otus.orm.exp.dao;

import otus.orm.exp.entity.Comment;

import java.util.List;

public interface CommentsDAO {
    Comment save(Comment comment);

    Comment findById(long id);

    List<Comment> findAll();

    boolean deleteById(long id);
}
