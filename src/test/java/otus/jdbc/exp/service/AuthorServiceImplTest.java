package otus.jdbc.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import otus.jdbc.exp.dao.AuthorsDAOImpl;
import otus.jdbc.exp.dao.BooksDAOImpl;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Book;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import({AuthorServiceImpl.class, AuthorsDAOImpl.class})
class AuthorServiceImplTest {

    @Autowired
    private AuthorsService service;

    private static final String AUTHOR_NAME = "Stephen King";
    private static final String AUTHOR_NAME_2 = "my name";
    private static final int LIST_SIZE_1 = 1;
    private static final int LIST_SIZE_2 = 2;
    private static final int LIST_SIZE_3 = 3;

    @Test
    public void getAuthorTest() {
        Optional<Author> book = service.getAuthorById(1);
        assertEquals(book.get().getName(), AUTHOR_NAME);
    }

    @Test
    public void getAllAuthorTest(){
        assertEquals(CollectionUtils.size(service.getAllAuthors()), LIST_SIZE_2);
    }

    @Test
    public void deleteAuthorTest() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            service.deleteAuthor(1);
        });
    }

    @Test
    public void saveAuthorTest() {
        Author author = new Author(4, AUTHOR_NAME_2);
        service.saveAuthor(author);
        assertEquals(service.getAllAuthors().size(), LIST_SIZE_3);
    }

    @Test
    public void updateAuthorTest() {
        Author author = new Author(1, AUTHOR_NAME_2);
        service.saveAuthor(author);
        assertEquals(service.getAuthorById(1).get().getName(), AUTHOR_NAME_2);
    }
}