package otus.orm.exp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.entity.Book;
import otus.orm.exp.repository.AuthorsRepository;
import otus.orm.exp.entity.Author;
import otus.orm.exp.repository.BooksRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorsService {

    private final AuthorsRepository authorsRepository;
    private final BooksRepository booksRepository;

    public AuthorServiceImpl(AuthorsRepository authorsRepository, BooksRepository booksRepository) {
        this.authorsRepository = authorsRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        List<Author> authors = authorsRepository.findAll();
        return CollectionUtils.isEmpty(authors) ? new ArrayList<>() : authors;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(String id) {
        return getAllAuthors().stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    @Override
    @Transactional
    public void deleteAuthor(String id) {
        authorsRepository.deleteById(id);
        List<Book> books = booksRepository.findAll();
        for(Book book:books){
            if (book.getAuthor().getId().equals(id)) {
                book.setAuthor(null);
                booksRepository.save(book);
            }
        }
    }


    @Override
    @Transactional
    public Optional<Author> saveAuthor(Author author) {
        return Optional.of(authorsRepository.save(author));
    }
}
