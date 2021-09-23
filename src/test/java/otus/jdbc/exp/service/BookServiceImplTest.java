package otus.jdbc.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import otus.jdbc.exp.dao.BooksDAOImpl;
import otus.jdbc.exp.entity.Book;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@JdbcTest
@Import({BooksDAOImpl.class, BookServiceImpl.class})
class BookServiceImplTest {

    private static final int LIST_SIZE_1 = 4;
    private static final int LIST_SIZE_2 = 2;
    private static final int AUTHOR_ID = 2;
    private static final int LIST_SIZE_3 = 3;
    private static final String BOOK_NAME = "Nothing";

    @Autowired
    private BooksService service;

    @Test
    public void getBookTest() {
        Optional<Book> book = service.getBookById(1);
        assertEquals(book.get().getIdAuthor(), AUTHOR_ID);
    }

    @Test
    public void getAllBooksTest(){
        assertEquals(CollectionUtils.size(service.getAllBooks()), LIST_SIZE_3);
    }

    @Test
    public void deleteBookTest() {
        service.deleteBook(1);
        assertEquals(service.getAllBooks().size(), LIST_SIZE_2);
    }

    @Test
    public void saveBookTest() {
        Book book = new Book(4, BOOK_NAME, 1, 2);
        service.saveBook(book);
        assertEquals(service.getAllBooks().size(), LIST_SIZE_1);
    }

    @Test
    public void updateBookTest() {
        Book book = new Book(1, BOOK_NAME, 1, 2);
        service.saveBook(book);
        assertEquals(service.getBookById(1).get().getName(), BOOK_NAME);
    }

}