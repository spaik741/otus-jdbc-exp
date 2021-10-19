package otus.orm.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.entity.Book;
import otus.orm.exp.repository.BooksRepository;
import otus.orm.exp.repository.GenresRepository;
import otus.orm.exp.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenresService {

    private final GenresRepository genresRepository;
    private final BooksRepository booksRepository;

    public GenreServiceImpl(GenresRepository genresRepository, BooksRepository booksRepository) {
        this.genresRepository = genresRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        List<Genre> genres = genresRepository.findAll();
        return CollectionUtils.isEmpty(genres) ? new ArrayList<>() : genres;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getGenreById(String id) {
        return getAllGenres().stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    @Override
    @Transactional
    public void deleteGenre(String id) {
        genresRepository.deleteById(id);
        List<Book> books = booksRepository.findAll();
        for(Book book:books){
            if (book.getGenre().getId().equals(id)) {
                book.setGenre(null);
                booksRepository.save(book);
            }
        }
    }

    @Override
    @Transactional
    public Optional<Genre> saveGenre(Genre genre) {
        return Optional.of(genresRepository.save(genre));
    }
}
