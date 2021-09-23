package otus.jdbc.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.jdbc.exp.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class BooksDAOImpl implements BooksDAO {

    private final NamedParameterJdbcOperations jdbc;

    public BooksDAOImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public boolean insert(Book book) {
        int answer = jdbc.update("insert into books (id, `name`, id_author, id_genre) values (:id, :name, :id_author, :id_genre)",
                Map.of("id", book.getId(), "name", book.getName(), "id_author", book.getIdAuthor(), "id_genre", book.getIdGenre()));
        return BooleanUtils.toBoolean(answer);
    }

    @Override
    public Book getById(long id) {
        return jdbc.queryForObject("select * from books where id = :id", Map.of("id", id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
    }

    @Override
    public boolean deleteById(long id) {
        int answer = jdbc.update("delete from books where id = :id", Map.of("id", id));
        return BooleanUtils.toBoolean(answer);
    }

    @Override
    public boolean update(Book book) {
        int answer = jdbc.update("update books set `name` = :name, id_author =:id_author, id_genre=:id_genre where id = :id",
                Map.of("id", book.getId(), "name", book.getName(), "id_author", book.getIdAuthor(), "id_genre", book.getIdGenre()));
        return BooleanUtils.toBoolean(answer);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long idAuth = resultSet.getLong("id_author");
            long idGenre = resultSet.getLong("id_genre");
            return new Book(id, name, idAuth, idGenre);
        }
    }
}
