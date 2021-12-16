package otus.orm.exp.repository;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import otus.orm.exp.entity.mongo.Author;
import otus.orm.exp.repository.mongo.AuthorsRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class AuthorsRepositoryImplTest {


    @Autowired
    private AuthorsRepository repository;
    @Autowired
    private MongoOperations mongoOperations;

    private static final String AUTHOR_FIRST_NAME = "name";
    private static final String FIRST = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";

    @Test
    public void getAuthorTest() {
        Author author = repository.findById(FIRST).get();
        Author expectedAuthor = mongoOperations.findById(FIRST, Author.class);
        assertThat(author).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    public void getAllAuthorTest() {
        assertThat(repository.findAll()).isNotEmpty()
                .allMatch(a -> StringUtils.isNotBlank(a.getFirstName()))
                .allMatch(a -> StringUtils.isNotBlank(a.getFirstName()));
    }

    @Test
    public void deleteAuthorTest() {
        repository.deleteById(TWO);
        assertThat(mongoOperations.findById(TWO, Author.class)).isNull();
    }

    @Test
    public void saveAuthorTest() {
        Author author = repository.save(new Author(THREE, AUTHOR_FIRST_NAME, "l"));
        Author expectedAuthor = mongoOperations.findById(THREE, Author.class);
        assertThat(author).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    public void updateAuthorTest() {
        Author author = repository.save(new Author(FIRST, AUTHOR_FIRST_NAME, "l"));
        Author expectedAuthor = mongoOperations.findById(FIRST, Author.class);
        assertThat(author).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}