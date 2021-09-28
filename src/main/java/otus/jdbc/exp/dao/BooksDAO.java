package otus.jdbc.exp.dao;

import otus.jdbc.exp.entity.Book;

import java.util.List;

public interface BooksDAO {

    Book save(Book book);

    Book findById(long id);

    List<Book> findAll();

    boolean deleteById(long id);

}
