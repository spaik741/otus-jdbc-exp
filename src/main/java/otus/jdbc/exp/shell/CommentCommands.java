package otus.jdbc.exp.shell;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Comment;
import otus.jdbc.exp.service.CommentService;
import otus.jdbc.exp.service.io.IOService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class CommentCommands {

    private final CommentService commentService;
    private final IOService ioService;

    public CommentCommands(CommentService commentService, IOService ioService) {
        this.commentService = commentService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "Save comment", key = {"sc", "saveComment", "ic", "insertComment"})
    public void save(long id, String message) {
        Comment comment = new Comment(id, message, new Date());
        if (commentService.saveComment(comment)) {
            ioService.printString("Коммент сохранен. " + comment);
        } else {
            ioService.printString("Коммент не сохранен. ");
        }
    }

    @ShellMethod(value = "Get author by id", key = {"gc", "getComment"})
    public void get(long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        String answerText = comment.map(value -> "Коммент получен. " + value).orElse("Коммент не получен.");
        ioService.printString(answerText);
    }

    @ShellMethod(value = "Get all author", key = {"gac", "getAllComment"})
    public void getAll() {
        List<Comment> comments = commentService.getAllComments();
        if (CollectionUtils.isNotEmpty(comments)) {
            ioService.printString("Комменты получены. " + comments);
        }else {
            ioService.printString("Комменты не найдены.");
        }
    }

    @ShellMethod(value = "Delete author", key = {"dc", "deleteComment"})
    public void delete(long id) {
        if(commentService.deleteComment(id)){
            ioService.printString(String.format("Коммент № [%s] удален. ", id));
        } else {
            ioService.printString("Коммент не удален. ");
        }
    }
}
