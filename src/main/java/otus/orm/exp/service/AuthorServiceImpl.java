package otus.orm.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.repository.AuthorsRepository;
import otus.orm.exp.entity.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorsService {

    private final AuthorsRepository repository;

    public AuthorServiceImpl(AuthorsRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        List<Author> authors = repository.findAll();
        return CollectionUtils.isEmpty(authors) ? new ArrayList<>() : authors;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(long id) {
        return getAllAuthors().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Author> saveAuthor(Author author) {
        return Optional.of(repository.save(author));
    }
}
