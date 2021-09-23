package otus.jdbc.exp.dao;

import otus.jdbc.exp.entity.Genre;

import java.util.List;

public interface GenresDAO {

    boolean insert(Genre book);

    Genre getById(long id);

    List<Genre> getAll();

    boolean deleteById(long id);

    boolean update(Genre book);
}
