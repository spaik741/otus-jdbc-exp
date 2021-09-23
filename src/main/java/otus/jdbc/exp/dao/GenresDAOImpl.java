package otus.jdbc.exp.dao;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.jdbc.exp.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class GenresDAOImpl implements GenresDAO{

    private final NamedParameterJdbcOperations jdbc;

    public GenresDAOImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public boolean insert(Genre genre) {
        int answer = jdbc.update("insert into genres (id, genre) values (:id, :genre)",
                Map.of("id", genre.getId(), "genre", genre.getGenre()));
        return BooleanUtils.toBoolean(answer);
    }

    @Override
    public Genre getById(long id) {
        return jdbc.queryForObject("select * from genres where id = :id", Map.of("id", id), new GenresMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenresMapper());
    }

    @Override
    public boolean deleteById(long id) {
        int answer = jdbc.update("delete from genres where id = :id", Map.of("id", id));
        return BooleanUtils.toBoolean(answer);
    }

    @Override
    public boolean update(Genre genre) {
        int answer = jdbc.update("update genres set genre = :genre where id = :id",
                Map.of("id", genre.getId(), "genre", genre.getGenre()));
        return BooleanUtils.toBoolean(answer);
    }

    private static class GenresMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("genre");
            return new Genre(id, name);
        }
    }
}
