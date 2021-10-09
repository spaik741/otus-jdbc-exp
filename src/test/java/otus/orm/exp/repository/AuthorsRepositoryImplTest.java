package otus.orm.exp.repository;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import otus.orm.exp.entity.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorsRepositoryImplTest {

    @Autowired
    private AuthorsRepository repository;
    @Autowired
    private TestEntityManager em;

    private static final String AUTHOR_FIRST_NAME = "Stephen";
    private static final String AUTHOR_FIRST_NAME_2 = "name";
    private static final int LIST_SIZE_2 = 2;
    private static final int LIST_SIZE_3 = 3;
    private static final long FIRST_AUTHOR = 1;
    private static final long TWO_AUTHOR = 3;

    @Test
    public void getAuthorTest() {
        Author author = repository.findById(FIRST_AUTHOR).get();
        Author expectedAuthor = em.find(Author.class, FIRST_AUTHOR);
        assertThat(author).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    public void getAllAuthorTest() {
        assertThat(repository.findAll()).hasSize(LIST_SIZE_2)
                .allMatch(a -> StringUtils.isNotBlank(a.getFirstName()))
                .allMatch(a -> StringUtils.isNotBlank(a.getFirstName()));
    }

    @Test
    public void deleteAuthorTest() {
        repository.deleteById(FIRST_AUTHOR);
    }

    @Test
    public void saveAuthorTest() {
        Author author = repository.save(new Author(TWO_AUTHOR, AUTHOR_FIRST_NAME_2, "l"));
        assertEquals(LIST_SIZE_3, repository.findAll().size());
        Author expectedAuthor = em.find(Author.class, TWO_AUTHOR);
        assertThat(author).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    public void updateAuthorTest() {
        Author author = repository.save(new Author(FIRST_AUTHOR, AUTHOR_FIRST_NAME_2, "l"));
        Author expectedAuthor = em.find(Author.class, FIRST_AUTHOR);
        assertThat(author).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}