package otus.jdbc.exp.shell;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.service.AuthorsService;
import otus.jdbc.exp.service.io.IOService;

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
    public void save(long id, String name) {
        Author author = new Author(id, name);
        if (authorsService.saveAuthor(author)) {
            ioService.printString("Автор сохранен. " + author);
        } else {
            ioService.printString("Автор не сохранен. ");
        }
    }

    @ShellMethod(value = "Get author by id", key = {"ga", "getAuthor"})
    public void get(long id) {
        Optional<Author> author = authorsService.getAuthorById(id);
        String answerText = author.map(value -> "Автор получен. " + value).orElse("Автор не получен.");
        ioService.printString(answerText);
    }

    @ShellMethod(value = "Get all author", key = {"gaa", "getAllAuthor"})
    public void getAll() {
        List<Author> authors = authorsService.getAllAuthors();
        if (CollectionUtils.isNotEmpty(authors)) {
            ioService.printString("Авторы получены. " + authors);
        }else {
            ioService.printString("Авторы не найдены.");
        }
    }

    @ShellMethod(value = "Delete author", key = {"da", "deleteAuthor"})
    public void delete(long id) {
        if(authorsService.deleteAuthor(id)){
            ioService.printString(String.format("Автор № [%s] удален. ", id));
        } else {
            ioService.printString("Автор не удален. ");
        }

    }
}
