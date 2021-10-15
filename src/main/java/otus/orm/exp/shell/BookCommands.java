package otus.orm.exp.shell;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.orm.exp.entity.Author;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Genre;
import otus.orm.exp.service.BooksService;
import otus.orm.exp.service.factory.BookFactory;
import otus.orm.exp.service.io.IOService;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class BookCommands {

    private final BooksService booksService;
    private final IOService ioService;
    private final BookFactory bookFactory;

    public BookCommands(BooksService booksService, IOService ioService, BookFactory bookFactory) {
        this.booksService = booksService;
        this.ioService = ioService;
        this.bookFactory = bookFactory;
    }

    @ShellMethod(value = "Save book", key = {"sb", "saveBook", "ib", "insertBook"})
    public void save(@ShellOption(value = "-n") String name,
                     @ShellOption(value = "-a") String idAuthor,
                     @ShellOption(value = "-d") String idGenre) {
        Optional<Book> book = bookFactory.createBook(name, idAuthor, idGenre);
        if (book.isPresent()) {
            try {
                booksService.saveBook(book.get());
                ioService.printString("Книга сохранена. " + book.get());
            }catch (Exception e) {
                ioService.printString("Книга не сохранена. ");
            }
        }
    }

    @ShellMethod(value = "Get book by id", key = {"gb", "getBook"})
    public void get(String id) {
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
    public void delete(String id) {
        try {
            booksService.deleteBook(id);
            ioService.printString(String.format("Книга № [%s] удалена. ", id));
        }catch (Exception e) {
            ioService.printString("Книга не удалена. ");
        }
    }

}
