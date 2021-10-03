package otus.orm.exp.service;

import otus.orm.exp.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(long id);

    boolean deleteAuthor(long id);

    boolean saveAuthor(Author author);
}
