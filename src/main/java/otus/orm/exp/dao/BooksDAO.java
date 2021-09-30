package otus.orm.exp.dao;

import otus.orm.exp.entity.Book;

import java.util.List;

public interface BooksDAO {

    Book save(Book book);

    Book findById(long id);

    List<Book> findAll();

    boolean deleteById(long id);

}
