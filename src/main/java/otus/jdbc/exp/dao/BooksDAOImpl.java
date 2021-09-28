package otus.jdbc.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Repository;
import otus.jdbc.exp.entity.Book;

import javax.persistence.*;
import java.util.List;

@Repository
public class BooksDAOImpl implements BooksDAO {

    private final NamedParameterJdbcOperations jdbc;

    public BooksDAOImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public boolean insert(Book book) {
        int answer = jdbc.update("insert into books (`name`, id_author, id_genre) values (:name, :id_author, :id_genre)",
                Map.of("name", book.getName(), "id_author", book.getAuthor().getId(), "id_genre", book.getGenre().getId()));
        return BooleanUtils.toBoolean(answer);
    }

    @Override
    public Book getById(long id) {
        List<Book> books = jdbc.query("select b.id, b.name, a.id id_author, a.f_name, a.l_name, g.id id_genre, g.genre " +
                "from (books b left join authors a on b.id_author = a.id) left join genres g on b.id_genre = g.id " +
                "where b.id = :id", Map.of("id", id), new BooksMapper());
        return books.stream().findFirst().get();
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = jdbc.query("select b.id, b.name, a.id id_author, a.f_name, a.l_name, g.id id_genre, g.genre " +
                "from (books b left join authors a on b.id_author = a.id) left join genres g on b.id_genre = g.id", new BooksMapper());
        return books;
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

    private static class BooksMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Genre genre = new Genre(resultSet.getLong("id_genre"), resultSet.getString("genre"));
            Author author = new Author(resultSet.getLong("id_author"),
                    resultSet.getString("f_name"),
                    resultSet.getString("l_name"));
            return new Book(id, name, author, genre);
        }
    }
}
