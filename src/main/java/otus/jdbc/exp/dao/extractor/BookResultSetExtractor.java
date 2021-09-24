package otus.jdbc.exp.dao.extractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import otus.jdbc.exp.entity.Author;
import otus.jdbc.exp.entity.Book;
import otus.jdbc.exp.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookResultSetExtractor implements ResultSetExtractor<Map<Long, Book>> {
    @Override
    public Map<Long, Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, Book> books = new HashMap<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            Book book = books.get(id);
            if (book == null) {
                book = new Book(id, resultSet.getString("name"),
                        new Author(resultSet.getLong("id_author"), resultSet.getString("f_name"), resultSet.getString("l_name")),
                        new Genre(resultSet.getLong("id_genre"), resultSet.getString("genre")));
                books.put(book.getId(), book);
            }
        }
        return books;
    }
}
