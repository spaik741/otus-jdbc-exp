package otus.orm.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.dao.CommentsDAO;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Comment;
import otus.orm.exp.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentsDAO dao;

    public CommentServiceImpl(CommentsDAO dao) {
        this.dao = dao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments(Book book) {
        return CollectionUtils.isEmpty(dao.findAllByBook(book)) ? new ArrayList<>() : dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(long id) {
        return dao.findAll().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public  Optional<Comment> saveComment(Comment comment) {
        return Optional.of(dao.save(comment));
    }
}
