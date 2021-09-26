package otus.jdbc.exp.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import otus.jdbc.exp.entity.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Import({AuthorsDAOImpl.class})
class AuthorsDAOImplTest {

    @Autowired
    private AuthorsDAO dao;

    private static final String AUTHOR_FIRST_NAME = "Stephen";
    private static final String AUTHOR_FIRST_NAME_2 = "name";
    private static final int LIST_SIZE_2 = 2;
    private static final int LIST_SIZE_3 = 3;

    @Test
    public void getAuthorTest() {
        Author author = dao.getById(1);
        assertEquals(author.getFirstName(), AUTHOR_FIRST_NAME);
    }

    @Test
    public void getAllAuthorTest() {
        assertEquals(CollectionUtils.size(dao.getAll()), LIST_SIZE_2);
    }

    @Test
    public void deleteAuthorTest() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            dao.deleteById(1);
        });
    }

    @Test
    public void saveAuthorTest() {
        Author author = new Author(4, AUTHOR_FIRST_NAME_2, "l");
        dao.insert(author);
        assertEquals(dao.getAll().size(), LIST_SIZE_3);
    }

    @Test
    public void updateAuthorTest() {
        Author author = new Author(1, AUTHOR_FIRST_NAME_2, "l");
        dao.update(author);
        assertEquals(dao.getById(1).getFirstName(), AUTHOR_FIRST_NAME_2);
    }
}