package otus.jdbc.exp.dao;

import otus.jdbc.exp.entity.Author;

import java.util.List;

public interface AuthorsDAO {

    Author save(Author author);

    Author findById(long id);

    List<Author> findAll();

    boolean deleteById(long id);

}
