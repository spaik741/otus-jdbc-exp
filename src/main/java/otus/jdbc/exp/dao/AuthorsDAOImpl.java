package otus.jdbc.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorsDAOImpl implements AuthorsDAO {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorsDAOImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean insert(Author author) {
        int answer = jdbc.update("insert into authors (id, `name`) values (:id, :name)",
                Map.of("id", author.getId(), "name", author.getName()));
        return BooleanUtils.toBoolean(answer);
    }

    @Override
    public Author getById(long id) {
        return jdbc.queryForObject("select * from authors where id = :id", Map.of("id", id), new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public boolean deleteById(long id) {
        int answer = jdbc.update("delete from authors where id = :id", Map.of("id", id));
        return BooleanUtils.toBoolean(answer);
    }

    @Override
    public boolean update(Author author) {
        int answer = jdbc.update("update authors set `name` = :name where id = :id",
                Map.of("id", author.getId(), "name", author.getName()));
        return BooleanUtils.toBoolean(answer);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
