package otus.orm.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.dao.CommentsDAO;
import otus.orm.exp.entity.Comment;

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
    public List<Comment> getAllComments() {
        return CollectionUtils.isEmpty(dao.findAll()) ? new ArrayList<>() : dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(long id) {
        return getAllComments().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public boolean deleteComment(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional
    public boolean saveComment(Comment comment) {
        return dao.save(comment) != null;
    }
}
