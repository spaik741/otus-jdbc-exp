package otus.orm.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.repository.CommentsRepository;
import otus.orm.exp.entity.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentsRepository repository;

    public CommentServiceImpl(CommentsRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments(long idBook) {
        List<Comment> comments = repository.findAllByBookId(idBook);
        return CollectionUtils.isEmpty(comments) ? new ArrayList<>() : comments;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(long id) {
        return repository.findAll().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public  Optional<Comment> saveComment(Comment comment) {
        return Optional.of(repository.save(comment));
    }
}
