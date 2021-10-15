package otus.orm.exp.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import otus.orm.exp.entity.Author;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Comment;
import otus.orm.exp.entity.Genre;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@ChangeLog
public class LibraryDbChangelog {

    private List<Genre> genreList;
    private List<Author> authorList;
    private List<Book> bookList;

    @ChangeSet(order = "001", id = "dropDb", author = "admin", runAlways = true)
    public void dropDb(MongoDatabase mongoDatabase) {
        mongoDatabase.drop();
    }

    @ChangeSet(order = "002", id = "insertInGenresDb", author = "admin", runAlways = true)
    public void insertValueInGenresDb(MongockTemplate template) {
        Genre genre1 = template.save(new Genre("1", "Fantasy"));
        Genre genre2 = template.save(new Genre("2", "Horror"));
        Genre genre3 = template.save(new Genre("3", "Teaching"));
        ;
        genreList = Arrays.asList(genre1, genre2, genre3);
    }

    @ChangeSet(order = "003", id = "insertInAuthorsDb", author = "admin", runAlways = true)
    public void insertValueInAuthorsDb(MongockTemplate template) {
        Author author1 = template.save(new Author("1", "Stephen", "King"));
        Author author2 = template.save(new Author("2", "Suzanne", "Collins"));
        authorList = Arrays.asList(author1, author2);
    }

    @ChangeSet(order = "004", id = "insertInBooksDb", author = "admin", runAlways = true)
    public void insertValueInBooksDb(MongockTemplate template) {
        Book book1 = template.save(new Book("1", "The Hunger Games", authorList.get(1), genreList.get(0)));
        Book book2 = template.save(new Book("2", "Pet Sematary", authorList.get(0), genreList.get(0)));
        Book book3 = template.save(new Book("3", "Shine", authorList.get(0), genreList.get(1)));
        bookList = Arrays.asList(book1, book2, book3);
    }

    @ChangeSet(order = "005", id = "insertInCommentsDb", author = "admin", runAlways = true)
    public void insertValueInCommentsDb(MongockTemplate template) {
        template.save(new Comment("1", "Could not tear myself away", new Date(), bookList.get(0)));
        template.save(new Comment("2", "So the book is great", new Date(), bookList.get(1)));
        template.save(new Comment("3", "I was bored sometimes, but overall ok", new Date(), bookList.get(2)));
    }
}
