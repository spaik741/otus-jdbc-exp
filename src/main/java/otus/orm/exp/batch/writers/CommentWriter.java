package otus.orm.exp.batch.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import otus.orm.exp.entity.h2.CommentTo;
import otus.orm.exp.repository.h2.BooksRepository;
import otus.orm.exp.repository.h2.CommentsRepository;

import java.util.List;

@Component
public class CommentWriter implements ItemWriter<CommentTo> {

    private final BooksRepository booksRepository;
    private final CommentsRepository commentsRepository;

    public CommentWriter(BooksRepository booksRepository, CommentsRepository commentsRepository) {
        this.booksRepository = booksRepository;
        this.commentsRepository = commentsRepository;
    }

    @Override
    public void write(List<? extends CommentTo> list) throws Exception {
        for (CommentTo comment: list) {
            if (booksRepository.getById(comment.getBookTo().getId()) != null) {
                commentsRepository.save(comment);
            }
        }
    }
}
