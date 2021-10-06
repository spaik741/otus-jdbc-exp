package otus.orm.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.dao.AuthorsDAO;
import otus.orm.exp.entity.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorsService {

    private final AuthorsDAO dao;

    public AuthorServiceImpl(AuthorsDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return CollectionUtils.isEmpty(dao.findAll()) ? new ArrayList<>() : dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(long id) {
        return getAllAuthors().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Author> saveAuthor(Author author) {
        return Optional.of(dao.save(author));
    }
}
