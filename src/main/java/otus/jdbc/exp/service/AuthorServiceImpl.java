package otus.jdbc.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
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
        return CollectionUtils.isEmpty(dao.getAll()) ? new ArrayList<>() : dao.getAll();
    }

    @Override
    public Optional<Author> getAuthorById(long id) {
        return getAllAuthors().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public boolean deleteAuthor(long id) {
        return dao.deleteById(id);
    }

    @Override
    public boolean saveAuthor(Author author) {
        if (getAuthorById(author.getId()).isPresent()) {
            return dao.update(author);
        } else {
            return dao.insert(author);
        }
    }
}
