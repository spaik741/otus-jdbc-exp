package otus.orm.exp.repository;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import otus.orm.exp.entity.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GenresRepositoryImplTest {

    @Autowired
    private GenresRepository repository;
    @Autowired
    private TestEntityManager em;

    private static final String GENRE = "genre";
    private static final int LIST_SIZE_1 = 3;
    private static final int LIST_SIZE_2 = 4;
    private static final long FIRST_GENRE = 1;
    private static final long TWO_GENRE = 4;

    @Test
    public void getGenreTest() {
        Genre genre = repository.findById(FIRST_GENRE).get();
        Genre expectedGenre = em.find(Genre.class, FIRST_GENRE);
        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    public void getAllGenreTest() {
        assertThat(repository.findAll()).hasSize(LIST_SIZE_1)
                .allMatch(g -> StringUtils.isNotBlank(g.getGenre()));
    }

    @Test
    public void deleteGenreTest() {
        repository.deleteById(FIRST_GENRE);
    }

    @Test
    public void saveGenreTest() {
        Genre genre = repository.save(new Genre(TWO_GENRE, GENRE));
        Genre expectedGenre = em.find(Genre.class, TWO_GENRE);
        assertEquals(LIST_SIZE_2, repository.findAll().size());
        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    public void updateGenreTest() {
        Genre genre = repository.save(new Genre(FIRST_GENRE, GENRE));
        Genre expectedGenre = em.find(Genre.class, FIRST_GENRE);
        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}