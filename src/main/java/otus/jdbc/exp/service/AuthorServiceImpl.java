package otus.jdbc.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.jdbc.exp.dao.AuthorsDAO;
import otus.jdbc.exp.entity.Author;

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
    public List<Author> getAllAuthors() {
        return CollectionUtils.isEmpty(dao.findAll()) ? new ArrayList<>() : dao.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(long id) {
        return getAllAuthors().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public boolean deleteAuthor(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional
    public boolean saveAuthor(Author author) {
        return dao.save(author) != null;
    }
}
