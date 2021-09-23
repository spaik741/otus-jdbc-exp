package otus.jdbc.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import otus.jdbc.exp.dao.BooksDAO;
import otus.jdbc.exp.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BooksService{

    private final BooksDAO dao;

    public BookServiceImpl(BooksDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Book> getAllBooks() {
        return CollectionUtils.isEmpty(dao.getAll())? new ArrayList<>(): dao.getAll();
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return getAllBooks().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public boolean deleteBook(long id) {
        return dao.deleteById(id);
    }

    @Override
    public boolean saveBook(Book book) {
        if (getBookById(book.getId()).isPresent()) {
            return dao.update(book);
        } else {
            return dao.insert(book);
        }
    }

}
