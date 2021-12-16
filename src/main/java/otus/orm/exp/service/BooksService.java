package otus.orm.exp.service;

import otus.orm.exp.entity.mongo.Book;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(String id);

    void deleteBook(String id);

    Optional<Book> saveBook(Book book);
}
