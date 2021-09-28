package otus.jdbc.exp.service;

import otus.jdbc.exp.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(long id);

    boolean deleteBook(long id);

    boolean saveBook(Book book);
}
