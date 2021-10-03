package otus.orm.exp.dao;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import otus.orm.exp.entity.Author;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import({BooksDAOImpl.class})
class BooksDAOImplTest {

    private static final int LIST_SIZE_1 = 4;
    private static final int LIST_SIZE_2 = 2;
    private static final int AUTHOR_ID = 2;
    private static final int LIST_SIZE_3 = 3;
    private static final String BOOK_NAME = "Nothing";
    private static final long FIRST_BOOK = 1;
    private static final long TWO_BOOK = 4;

    @Autowired
    private BooksDAO dao;

    @Autowired
    private TestEntityManager em;

    @Test
    public void getBookTest() {
        Book book = dao.findById(FIRST_BOOK);
        Book expectedBook = em.find(Book.class, FIRST_BOOK);
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    public void getAllBooksTest() {
        assertThat(dao.findAll()).hasSize(LIST_SIZE_3)
                .allMatch(b -> StringUtils.isNotBlank(b.getName()))
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
    }

    @Test
    public void deleteBookTest() {
        assertTrue(dao.deleteById(FIRST_BOOK));
        assertThat(em.find(Book.class, FIRST_BOOK)).isNull();
    }

    @Test
    public void saveBookTest() {
        Book book = dao.save(new Book(TWO_BOOK, BOOK_NAME, new Author(1L, "a", "b"), new Genre(1L, "b")));
        Book expectedBook = em.find(Book.class, TWO_BOOK);
        assertEquals(TWO_BOOK, dao.findAll().size());
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    public void updateBookTest() {
        Book book = dao.save(new Book(FIRST_BOOK, BOOK_NAME, new Author(1, "a", "b"), new Genre(1, "b")));
        Book expectedBook = em.find(Book.class, FIRST_BOOK);
        assertEquals(BOOK_NAME, dao.findById(1).getName());
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}