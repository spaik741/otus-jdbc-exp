package otus.orm.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.dao.GenresDAO;
import otus.orm.exp.entity.Genre;

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
        return CollectionUtils.isEmpty(dao.findAll())? new ArrayList<>(): dao.findAll();
    }

    @Override
    public Optional<Genre> getGenreById(long id) {
        return getAllGenres().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public boolean deleteGenre(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional
    public boolean saveGenre(Genre genre) {

            return dao.save(genre) != null;

    }
}
