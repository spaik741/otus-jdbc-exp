package otus.jdbc.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public boolean deleteBook(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional
    public boolean saveBook(Book book) {
        return dao.save(book) != null;
    }

}
