package otus.orm.exp.service;

import otus.orm.exp.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenresService {

    List<Genre> getAllGenres();

    Optional<Genre> getGenreById(String id);

    void deleteGenre(String id);

    Optional<Genre> saveGenre(Genre genre);
}
