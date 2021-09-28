package otus.jdbc.exp.dao;

import otus.jdbc.exp.entity.Book;

import java.util.List;

public interface BooksDAO {

    boolean insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    boolean deleteById(long id);

    boolean update(Book book);
}
