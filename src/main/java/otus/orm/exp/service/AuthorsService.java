package otus.orm.exp.service;

import otus.orm.exp.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(String id);

    void deleteAuthor(String id);

    Optional<Author> saveAuthor(Author author);
}
