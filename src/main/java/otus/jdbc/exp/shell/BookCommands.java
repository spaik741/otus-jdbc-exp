package otus.jdbc.exp.shell;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Book;
import otus.jdbc.exp.entity.Comment;
import otus.jdbc.exp.entity.Genre;
import otus.jdbc.exp.service.AuthorsService;
import otus.jdbc.exp.service.BooksService;
import otus.jdbc.exp.service.CommentService;
import otus.jdbc.exp.service.GenresService;
import otus.jdbc.exp.service.io.IOService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
public class BookCommands {

    private final AuthorsService authorsService;
    private final GenresService genresService;
    private final BooksService booksService;
    private final CommentService commentService;
    private final IOService ioService;

    public BookCommands(AuthorsService authorsService, GenresService genresService, BooksService booksService, CommentService commentService, IOService ioService) {
        this.authorsService = authorsService;
        this.genresService = genresService;
        this.booksService = booksService;
        this.commentService = commentService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "Save book", key = {"sb", "saveBook", "ib", "insertBook"})
    public void save(@ShellOption(value = "-i") long id,
                     @ShellOption(value = "-n") String name,
                     @ShellOption(value = "-a") long idAuthor,
                     @ShellOption(value = "-d") long idGenre,
                     @ShellOption(value = "-c", arity = 1) long[] comments) {
        Optional<Author> author = authorsService.getAuthorById(idAuthor);
        Optional<Genre> genre = genresService.getGenreById(idGenre);
        if (author.isPresent() && genre.isPresent()) {
            Book book = new Book(id, name, author.get(), genre.get());
            List<Optional<Comment>> listComment = Arrays.stream(comments).mapToObj(commentService::getCommentById).collect(Collectors.toList());
            book.setComment(listComment.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
            if (booksService.saveBook(book)) {
                ioService.printString("Книга сохранена. " + book);
                return;
            }
            ioService.printString("Книга не сохранена. ");
        }
    }

    @ShellMethod(value = "Get book by id", key = {"gb", "getBook"})
    public void get(long id) {
        Optional<Book> book = booksService.getBookById(id);
        String answerText = book.map(value -> "Книга получена. " + value).orElse("Книга не получена.");
        ioService.printString(answerText);
    }

    @ShellMethod(value = "Get all book", key = {"gab", "getAllBook"})
    public void getAll() {
        List<Book> books = booksService.getAllBooks();
        if (CollectionUtils.isNotEmpty(books)) {
            ioService.printString("Книги получены. " + books);
        } else {
            ioService.printString("Книги не найдены.");
        }
    }

    @ShellMethod(value = "Delete book", key = {"db", "deleteBook"})
    public void delete(long id) {
        if (booksService.deleteBook(id)) {
            ioService.printString(String.format("Книга № [%s] удалена. ", id));
        } else {
            ioService.printString("Книга не удалена. ");
        }
    }

}
