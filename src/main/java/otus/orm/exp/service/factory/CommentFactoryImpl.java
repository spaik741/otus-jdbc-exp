package otus.orm.exp.service.factory;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Comment;
import otus.orm.exp.service.BooksService;

import java.util.Date;
import java.util.Optional;

@Component
public class CommentFactoryImpl implements CommentFactory {

    private final BooksService booksService;

    public CommentFactoryImpl(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> createComment(String text, Date date, String idBook) {
        Comment comment = null;
        Optional<Book> bookOptional = booksService.getBookById(idBook);
        if (bookOptional.isPresent()) {
            comment = new Comment(null, text, new Date(), bookOptional.get());
        }
        return Optional.ofNullable(comment);
    }
}
