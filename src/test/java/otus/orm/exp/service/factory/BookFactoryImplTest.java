package otus.orm.exp.service.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.orm.exp.entity.Author;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Genre;
import otus.orm.exp.service.AuthorsService;
import otus.orm.exp.service.GenresService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class BookFactoryImplTest {

    private final static Long LONG_VAL = 1L;

    @MockBean
    private AuthorsService authorsService;
    private GenresService genresService;
    private BookFactory bookFactory;

    @BeforeEach
    void init() {
        authorsService = mock(AuthorsService.class);
        genresService = mock(GenresService.class);
        bookFactory = new BookFactoryImpl(authorsService, genresService);
    }

    @Test
    public void testCreateBook() {
        given(authorsService.getAuthorById(LONG_VAL)).willReturn(Optional.of(new Author()));
        given(genresService.getGenreById(LONG_VAL)).willReturn(Optional.of(new Genre()));
        Optional<Book> book = bookFactory.createBook("name", LONG_VAL, LONG_VAL);
        assertThat(book.get()).isNotNull().matches(b -> b.getName().equals("name"));
    }

}