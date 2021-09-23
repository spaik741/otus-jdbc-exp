package otus.jdbc.exp.service;

import otus.jdbc.exp.entity.Book;
import otus.jdbc.exp.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenresService {

    List<Genre> getAllGenres();

    Optional<Genre> getGenreById(long id);

    boolean deleteGenre(long id);

    boolean saveGenre(Genre genre);
}
