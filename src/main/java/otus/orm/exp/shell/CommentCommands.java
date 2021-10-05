package otus.orm.exp.shell;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Comment;
import otus.orm.exp.service.BooksService;
import otus.orm.exp.service.CommentService;
import otus.orm.exp.service.factory.CommentFactory;
import otus.orm.exp.service.io.IOService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class CommentCommands {

    private final CommentService commentService;
    private final BooksService booksService;
    private final CommentFactory commentFactory;
    private final IOService ioService;

    public CommentCommands(CommentService commentService, BooksService booksService, CommentFactory commentFactory, IOService ioService) {
        this.commentService = commentService;
        this.booksService = booksService;
        this.commentFactory = commentFactory;
        this.ioService = ioService;
    }

    @ShellMethod(value = "Save comment", key = {"sc", "saveComment", "ic", "insertComment"})
    public void save(String message, long idBook) {
        Optional<Comment> comment = commentFactory.createComment(message, new Date(), idBook);
        if (comment.isPresent()) {
            try {
                commentService.saveComment(comment.get());
                ioService.printString("Коммент сохранен. " + comment.get());
            } catch (Exception e) {
                ioService.printString("Коммент не сохранен. ");
            }
        }
    }

    @ShellMethod(value = "Get comment by id", key = {"gc", "getComment"})
    public void get(long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        String answerText = comment.map(value -> "Коммент получен. " + value).orElse("Коммент не получен.");
        ioService.printString(answerText);
    }

    @ShellMethod(value = "Get all comments", key = {"gac", "getAllComment"})
    public void getAll(long idBook) {
        Optional<Book> bookOptional = booksService.getBookById(idBook);
        if (bookOptional.isPresent()) {
            List<Comment> comments = commentService.getAllComments(bookOptional.get());
            if (CollectionUtils.isNotEmpty(comments)) {
                ioService.printString("Комменты получены. " + comments);
                return;
            }
        }
        ioService.printString("Комменты не найдены.");
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "deleteComment"})
    public void delete(long id) {
        try {
            commentService.deleteComment(id);
            ioService.printString(String.format("Коммент № [%s] удален. ", id));
        } catch (Exception e) {
            ioService.printString("Коммент не удален. ");
        }
    }
}
