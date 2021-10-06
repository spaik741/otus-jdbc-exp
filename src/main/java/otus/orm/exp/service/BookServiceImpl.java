package otus.orm.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.dao.BooksDAO;
import otus.orm.exp.entity.Book;

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
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return CollectionUtils.isEmpty(dao.findAll())? new ArrayList<>(): dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(long id) {
        return getAllBooks().stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    @Transactional
    public void deleteBook(long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Book> saveBook(Book book) {
        return Optional.of(dao.save(book));
    }

}
