package otus.orm.exp.dao;

import otus.orm.exp.entity.Author;

import java.util.List;

public interface AuthorsDAO {

    Author save(Author author);

    Author findById(long id);

    List<Author> findAll();

    boolean deleteById(long id);

}
