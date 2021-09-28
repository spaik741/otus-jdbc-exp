package otus.jdbc.exp.service;

import otus.jdbc.exp.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(long id);

    boolean deleteAuthor(long id);

    boolean saveAuthor(Author author);
}
