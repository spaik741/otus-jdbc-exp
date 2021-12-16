package otus.orm.exp.repository;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import otus.orm.exp.entity.mongo.Author;
import otus.orm.exp.entity.mongo.Book;
import otus.orm.exp.entity.mongo.Genre;
import otus.orm.exp.repository.mongo.BooksRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class BooksRepositoryImplTest {

    private static final String BOOK_NAME = "Nothing";
    private static final String FIRST = "1";
    private static final String TWO = "2";
    private static final String FOUR = "4";

    @Autowired
    private BooksRepository repository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void getBookTest() {
        Book book = repository.findById(FIRST).get();
        Book expectedBook = mongoOperations.findById(FIRST, Book.class);
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    public void getAllBooksTest() {
        assertThat(repository.findAll()).isNotEmpty()
                .allMatch(b -> StringUtils.isNotBlank(b.getName()))
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getGenre() != null);
    }

    @Test
    public void deleteBookTest() {
        repository.deleteById(TWO);
        assertThat(mongoOperations.findById(TWO, Book.class)).isNull();
    }

    @Test
    public void saveBookTest() {
        Book book = repository.save(new Book(FOUR, BOOK_NAME, new Author(FIRST, "a", "b"), new Genre(FIRST, "b")));
        Book expectedBook = mongoOperations.findById(FOUR, Book.class);
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    public void updateBookTest() {
        Book book = repository.save(new Book(FIRST, BOOK_NAME, new Author(FIRST, "a", "b"), new Genre(FIRST, "b")));
        Book expectedBook = mongoOperations.findById(FIRST, Book.class);
        assertEquals(BOOK_NAME, repository.findById(FIRST).get().getName());
        assertThat(book).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}