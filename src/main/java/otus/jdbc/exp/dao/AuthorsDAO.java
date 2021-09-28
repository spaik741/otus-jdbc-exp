package otus.jdbc.exp.dao;

import otus.jdbc.exp.entity.Author;

import java.util.List;

public interface AuthorsDAO {

    boolean insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    boolean deleteById(long id);

    boolean update(Author author);
}
