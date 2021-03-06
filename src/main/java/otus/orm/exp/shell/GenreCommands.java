package otus.orm.exp.shell;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.orm.exp.entity.Genre;
import otus.orm.exp.service.GenresService;
import otus.orm.exp.service.io.IOService;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class GenreCommands {

    private final GenresService genresService;
    private final IOService ioService;

    public GenreCommands(GenresService genresService, IOService ioService) {
        this.genresService = genresService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "Save genre", key = {"sg", "saveGenre", "ig", "insertGenre"})
    public void save(String id, String name) {
        Genre genre = new Genre(id, name);
        try {
            genresService.saveGenre(genre);
            ioService.printString("Жанр сохранен. " + genre);
        }catch (Exception e){
            ioService.printString("Жанр не сохранен. ");
        }
    }

    @ShellMethod(value = "Get genre by id", key = {"gg", "getGenre"})
    public void get(String id) {
        Optional<Genre> genre = genresService.getGenreById(id);
        String answerText = genre.map(value -> "Жанр получен. " + value).orElse("Жанр не получен.");
        ioService.printString(answerText);
    }

    @ShellMethod(value = "Get all genre", key = {"gag", "getAllGenre"})
    public void getAll() {
        List<Genre> genres = genresService.getAllGenres();
        if (CollectionUtils.isNotEmpty(genres)) {
            ioService.printString("Жанры получены. " + genres);
        } else {
            ioService.printString("Жанры не найдены.");
        }
    }

    @ShellMethod(value = "Delete genre", key = {"dg", "deleteGenre"})
    public void delete(String id) {
        try {
            genresService.deleteGenre(id);
            ioService.printString(String.format("Жанр № [%s] удален. ", id));
        }catch (Exception e){
            ioService.printString("Жанр не удален. ");
        }
    }
}
