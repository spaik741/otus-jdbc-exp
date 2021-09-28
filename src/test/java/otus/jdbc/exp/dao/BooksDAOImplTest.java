package otus.jdbc.exp.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Book;
import otus.jdbc.exp.entity.Comment;
import otus.jdbc.exp.entity.Genre;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
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
        Book book = dao.findById(1);
        assertEquals(AUTHOR_ID, book.getAuthor().getId());
    }

    @Test
    public void getAllBooksTest() {
        assertEquals(LIST_SIZE_3, CollectionUtils.size(dao.findAll()));
    }

    @Test
    public void deleteBookTest() {
        dao.deleteById(1);
        assertEquals(LIST_SIZE_2, dao.findAll().size());
    }

    @Test
    public void saveBookTest() {
        Book book = new Book(4L, BOOK_NAME, new Author(1, "a", "b"), new Genre(1, "b"));
        dao.save(book);
        assertEquals(LIST_SIZE_1, dao.findAll().size());
    }

    @Test
    public void updateBookTest() {
        Book book = new Book(1L, BOOK_NAME, new Author(1, "a", "b"), new Genre(1, "b"));
        dao.save(book);
        assertEquals(BOOK_NAME, dao.findById(1).getName());
    }
}