package otus.jdbc.exp.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import otus.jdbc.exp.entity.Genre;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
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
        Genre genre = dao.findById(1);
        assertEquals(genre.getGenre(), GENRE);
    }

    @Test
    public void getAllGenreTest() {
        assertEquals(CollectionUtils.size(dao.findAll()), LIST_SIZE_1);
    }

    @Test
    public void deleteGenreTest() {
        assertThrows(PersistenceException.class, () -> {
            dao.deleteById(1);
        });
    }

    @Test
    public void saveGenreTest() {
        Genre genre = new Genre(4, GENRE_2);
        dao.save(genre);
        assertEquals(dao.findAll().size(), LIST_SIZE_2);
    }

    @Test
    public void updateGenreTest() {
        Genre genry = new Genre(1, GENRE_2);
        dao.save(genry);
        assertEquals(dao.findById(1).getGenre(), GENRE_2);
    }
}