package otus.jdbc.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.jdbc.exp.dao.extractor.BookResultSetExtractor;
import otus.jdbc.exp.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class BooksDAOImpl implements BooksDAO {

    private final NamedParameterJdbcOperations jdbc;

    public BooksDAOImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public boolean insert(Book book) {
        int answer = jdbc.update("insert into books (id, `name`, id_author, id_genre) values (:id, :name, :id_author, :id_genre)",
                Map.of("id", book.getId(), "name", book.getName(), "id_author", book.getAuthor().getId(), "id_genre", book.getGenre().getId()));
        return BooleanUtils.toBoolean(answer);
    }

    @Override
    public Book getById(long id) {
        Map<Long, Book> books = jdbc.query("select b.id, b.name, a.id id_author, a.f_name, a.l_name, g.id id_genre, g.genre " +
                "from (books b left join authors a on b.id_author = a.id) left join genres g on b.id_genre = g.id " +
                "where id = :id", Map.of("id", id), new BookResultSetExtractor());
        return Objects.requireNonNull(books).values().stream().findFirst().get();
    }

    @Override
    public List<Book> getAll() {
        Map<Long, Book> books = jdbc.query("select b.id, b.name, a.id id_author, a.f_name, a.l_name, g.id id_genre, g.genre " +
                "from (books b left join authors a on b.id_author = a.id) left join genres g on b.id_genre = g.id", new BookResultSetExtractor());
        return new ArrayList<>(Objects.requireNonNull(books).values());
    }

    @Override
    public boolean deleteById(long id) {
        int answer = jdbc.update("delete from books where id = :id", Map.of("id", id));
        return BooleanUtils.toBoolean(answer);
    }

    @Override
    public boolean update(Book book) {
        int answer = jdbc.update("update books set `name` = :name, id_author =:id_author, id_genre=:id_genre where id = :id",
                Map.of("id", book.getId(), "name", book.getName(), "id_author", book.getAuthor().getId(), "id_genre", book.getGenre().getId()));
        return BooleanUtils.toBoolean(answer);
    }
}
