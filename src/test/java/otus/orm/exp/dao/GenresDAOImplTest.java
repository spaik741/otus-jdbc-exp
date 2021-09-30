package otus.orm.exp.dao;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import otus.orm.exp.entity.Genre;

import javax.persistence.PersistenceException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({GenresDAOImpl.class})
class GenresDAOImplTest {

    @Autowired
    private GenresDAO dao;
    @Autowired
    private TestEntityManager em;

    private static final String GENRE = "genre";
    private static final int LIST_SIZE_1 = 3;
    private static final int LIST_SIZE_2 = 4;
    private static final long FIRST_GENRE = 1;
    private static final long TWO_GENRE = 4;

    @Test
    public void getGenreTest() {
        Genre genre = dao.findById(FIRST_GENRE);
        Genre expectedGenre = em.find(Genre.class, FIRST_GENRE);
        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    public void getAllGenreTest() {
        assertThat(dao.findAll()).hasSize(LIST_SIZE_1)
                .allMatch(g -> StringUtils.isNotBlank(g.getGenre()));
    }

    @Test
    public void deleteGenreTest() {
        assertThrows(PersistenceException.class, () -> {
            dao.deleteById(1);
        });
    }

    @Test
    public void saveGenreTest() {
        Genre genre = dao.save(new Genre(TWO_GENRE, GENRE));
        Genre expectedGenre = em.find(Genre.class, TWO_GENRE);
        assertEquals(LIST_SIZE_2, dao.findAll().size());
        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    public void updateGenreTest() {
        Genre genre = dao.save(new Genre(FIRST_GENRE, GENRE));
        Genre expectedGenre = em.find(Genre.class, FIRST_GENRE);
        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}