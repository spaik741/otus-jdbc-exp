package otus.orm.exp.repository;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import otus.orm.exp.entity.Author;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BooksRepositoryImplTest {

    private static final int LIST_SIZE_3 = 3;
    private static final String BOOK_NAME = "Nothing";
    private static final long FIRST = 1;
    private static final long TWO_BOOK = 4;

    @Autowired
    private BooksRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void getBookTest() {
        Book book = repository.findById(FIRST).get();
        Book expectedBook = em.find(Book.class, FIRST);
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    public void getAllBooksTest() {
        assertThat(repository.findAll()).hasSize(LIST_SIZE_3)
                .allMatch(b -> StringUtils.isNotBlank(b.getName()))
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
    }

    @Test
    public void deleteBookTest() {
        repository.deleteById(FIRST);
        assertThat(em.find(Book.class, FIRST)).isNull();
    }

    @Test
    public void saveBookTest() {
        Book book = repository.save(new Book(TWO_BOOK, BOOK_NAME, new Author(FIRST, "a", "b"), new Genre(FIRST, "b")));
        Book expectedBook = em.find(Book.class, TWO_BOOK);
        assertEquals(TWO_BOOK, repository.findAll().size());
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    public void updateBookTest() {
        Book book = repository.save(new Book(FIRST, BOOK_NAME, new Author(FIRST, "a", "b"), new Genre(FIRST, "b")));
        Book expectedBook = em.find(Book.class, FIRST);
        assertEquals(BOOK_NAME, repository.findById(1).get().getName());
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}