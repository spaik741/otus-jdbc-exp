package otus.jdbc.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import otus.jdbc.exp.dao.AuthorsDAOImpl;
import otus.jdbc.exp.dao.GenresDAOImpl;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Genre;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Import({GenreServiceImpl.class, GenresDAOImpl.class})
class GenreServiceImplTest {

    @Autowired
    private GenresService service;

    private static final String GENRE = "Fantasy";
    private static final String GENRE_2 = "genre";
    private static final int LIST_SIZE_1 = 2;
    private static final int LIST_SIZE_2 = 3;
    private static final int LIST_SIZE_3 = 4;

    @Test
    public void getGenreTest() {
        Optional<Genre> genre = service.getGenreById(1);
        assertEquals(genre.get().getGenre(), GENRE);
    }

    @Test
    public void getAllGenreTest(){
        assertEquals(CollectionUtils.size(service.getAllGenres()), LIST_SIZE_2);
    }

    @Test
    public void deleteGenreTest() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            service.deleteGenre(1);
        });
    }

    @Test
    public void saveGenreTest() {
        Genre genre = new Genre(4, GENRE_2);
        service.saveGenre(genre);
        assertEquals(service.getAllGenres().size(), LIST_SIZE_3);
    }

    @Test
    public void updateGenreTest() {
        Genre genry = new Genre(1, GENRE_2);
        service.saveGenre(genry);
        assertEquals(service.getGenreById(1).get().getGenre(), GENRE_2);
    }
}