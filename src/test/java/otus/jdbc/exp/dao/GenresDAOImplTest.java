package otus.jdbc.exp.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import otus.jdbc.exp.entity.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Import({GenresDAOImpl.class})
class GenresDAOImplTest {

    @Autowired
    private GenresDAO dao;

    private static final String GENRE = "Fantasy";
    private static final String GENRE_2 = "genre";
    private static final int LIST_SIZE_1 = 3;
    private static final int LIST_SIZE_2 = 4;

    @Test
    public void getGenreTest() {
        Genre genre = dao.getById(1);
        assertEquals(genre.getGenre(), GENRE);
    }

    @Test
    public void getAllGenreTest() {
        assertEquals(CollectionUtils.size(dao.getAll()), LIST_SIZE_1);
    }

    @Test
    public void deleteGenreTest() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            dao.deleteById(1);
        });
    }

    @Test
    public void saveGenreTest() {
        Genre genre = new Genre(4, GENRE_2);
        dao.insert(genre);
        assertEquals(dao.getAll().size(), LIST_SIZE_2);
    }

    @Test
    public void updateGenreTest() {
        Genre genry = new Genre(1, GENRE_2);
        dao.update(genry);
        assertEquals(dao.getById(1).getGenre(), GENRE_2);
    }
}