package otus.jdbc.exp.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Book;
import otus.jdbc.exp.entity.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import({BooksDAOImpl.class})
class BooksDAOImplTest {

    private static final int LIST_SIZE_1 = 4;
    private static final int LIST_SIZE_2 = 2;
    private static final int AUTHOR_ID = 2;
    private static final int LIST_SIZE_3 = 3;
    private static final String BOOK_NAME = "Nothing";

    @Autowired
    private BooksDAO dao;

    @Test
    public void getBookTest() {
        Book book = dao.getById(1);
        assertEquals(book.getAuthor().getId(), AUTHOR_ID);
    }

    @Test
    public void getAllBooksTest() {
        assertEquals(CollectionUtils.size(dao.getAll()), LIST_SIZE_3);
    }

    @Test
    public void deleteBookTest() {
        dao.deleteById(1);
        assertEquals(dao.getAll().size(), LIST_SIZE_2);
    }

    @Test
    public void saveBookTest() {
        Book book = new Book(4, BOOK_NAME, new Author(1, "a", "b"), new Genre(1, "b"));
        dao.insert(book);
        assertEquals(dao.getAll().size(), LIST_SIZE_1);
    }

    @Test
    public void updateBookTest() {
        Book book = new Book(1, BOOK_NAME, new Author(1, "a", "b"), new Genre(1, "b"));
        dao.update(book);
        assertEquals(dao.getById(1).getName(), BOOK_NAME);
    }
}