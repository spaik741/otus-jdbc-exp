package otus.orm.exp.shell;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.orm.exp.entity.Author;
import otus.orm.exp.service.AuthorsService;
import otus.orm.exp.service.io.IOService;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class AuthorCommands {

    private final AuthorsService authorsService;
    private final IOService ioService;

    public AuthorCommands(AuthorsService authorsService, IOService ioService) {
        this.authorsService = authorsService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "Save author", key = {"sa", "saveAuthor", "ia", "insertAuthor"})
    public void save(String id, String firstName, String lastName) {
        Author author = new Author(id, firstName, lastName);
        try {
            authorsService.saveAuthor(author);
            ioService.printString("Автор сохранен. " + author);
        } catch (Exception e) {
            ioService.printString("Автор не сохранен. ");
        }
    }

    @ShellMethod(value = "Get author by id", key = {"ga", "getAuthor"})
    public void get(String id) {
        Optional<Author> author = authorsService.getAuthorById(id);
        String answerText = author.map(value -> "Автор получен. " + value).orElse("Автор не получен.");
        ioService.printString(answerText);
    }

    @ShellMethod(value = "Get all author", key = {"gaa", "getAllAuthor"})
    public void getAll() {
        List<Author> authors = authorsService.getAllAuthors();
        if (CollectionUtils.isNotEmpty(authors)) {
            ioService.printString("Авторы получены. " + authors);
        } else {
            ioService.printString("Авторы не найдены.");
        }
    }

    @ShellMethod(value = "Delete author", key = {"da", "deleteAuthor"})
    public void delete(String id) {
        try {
            authorsService.deleteAuthor(id);
            ioService.printString(String.format("Автор № [%s] удален. ", id));
        } catch (Exception e) {
            ioService.printString("Автор не удален. ");
        }
    }
}
