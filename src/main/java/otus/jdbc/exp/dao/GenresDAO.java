package otus.jdbc.exp.dao;

import otus.jdbc.exp.entity.Genre;

import java.util.List;

public interface GenresDAO {

    Genre save(Genre book);

    Genre findById(long id);

    List<Genre> findAll();

    boolean deleteById(long id);

}
