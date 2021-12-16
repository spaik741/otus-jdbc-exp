package otus.orm.exp.repository;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import otus.orm.exp.entity.mongo.Genre;
import otus.orm.exp.repository.mongo.GenresRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
class GenresRepositoryImplTest {

    @Autowired
    private GenresRepository repository;
    @Autowired
    private MongoOperations mongoOperations;

    private static final String GENRE = "genre";
    private static final int LIST_SIZE_1 = 3;
    private static final int LIST_SIZE_2 = 4;
    private static final String FIRST_GENRE = "1";
    private static final String TWO_GENRE = "4";

    @Test
    public void getGenreTest() {
        Genre genre = repository.findById(FIRST_GENRE).get();
        Genre expectedGenre = mongoOperations.findById(FIRST_GENRE, Genre.class);
        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    public void getAllGenreTest() {
        assertThat(repository.findAll()).isNotEmpty()
                .allMatch(g -> StringUtils.isNotBlank(g.getGenre()));
    }

    @Test
    public void deleteGenreTest() {
        repository.deleteById(FIRST_GENRE);
    }

    @Test
    public void saveGenreTest() {
        Genre genre = repository.save(new Genre(TWO_GENRE, GENRE));
        Genre expectedGenre = mongoOperations.findById(TWO_GENRE, Genre.class);
        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    public void updateGenreTest() {
        Genre genre = repository.save(new Genre(FIRST_GENRE, GENRE));
        Genre expectedGenre = mongoOperations.findById(FIRST_GENRE, Genre.class);
        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}