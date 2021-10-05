package otus.orm.exp.service.factory;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.entity.Author;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Genre;
import otus.orm.exp.service.AuthorsService;
import otus.orm.exp.service.GenresService;

import java.util.Optional;

@Component
public class BookFactoryImpl implements BookFactory {

    private final AuthorsService authorsService;
    private final GenresService genresService;

    public BookFactoryImpl(AuthorsService authorsService, GenresService genresService) {
        this.authorsService = authorsService;
        this.genresService = genresService;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> createBook(String name, long idAuthor, long idGenre) {
        Optional<Author> author = authorsService.getAuthorById(idAuthor);
        Optional<Genre> genre = genresService.getGenreById(idGenre);
        Book book = null;
        if (author.isPresent() && genre.isPresent()) {
            book = new Book(null, name, author.get(), genre.get());
        }
        return Optional.ofNullable(book);
    }
}
