package otus.jdbc.exp.shell;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.jdbc.exp.entity.Book;
import otus.jdbc.exp.service.BooksService;
import otus.jdbc.exp.service.io.IOService;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class BookCommands {

    private final BooksService booksService;
    private final IOService ioService;

    public BookCommands(BooksService booksService, IOService ioService) {
        this.booksService = booksService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "Save book", key = {"sb", "saveBook", "ib", "insertBook"})
    public void save(long id, String name, long idAuthor, long idGenre) {
        Book book = new Book(id, name, idAuthor, idGenre);
        if (booksService.saveBook(book)) {
            ioService.printString("Книга сохранена. " + book);
        } else {
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
        if(booksService.deleteBook(id)){
            ioService.printString(String.format("Книга № [%s] удалена. ", id));
        } else {
            ioService.printString("Книга не удалена. ");
        }
    }

}
