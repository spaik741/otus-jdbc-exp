package otus.jdbc.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import otus.jdbc.exp.dao.GenresDAO;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenresService{

    private final GenresDAO dao;

    public GenreServiceImpl(GenresDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Genre> getAllGenres() {
        return CollectionUtils.isEmpty(dao.getAll())? new ArrayList<>(): dao.getAll();
    }

    @Override
    public Optional<Genre> getGenreById(long id) {
        return getAllGenres().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public boolean deleteGenre(long id) {
        return dao.deleteById(id);
    }

    @Override
    public boolean saveGenre(Genre genre) {
        if (getGenreById(genre.getId()).isPresent()) {
            return dao.update(genre);
        } else {
            return dao.insert(genre);
        }
    }
}
