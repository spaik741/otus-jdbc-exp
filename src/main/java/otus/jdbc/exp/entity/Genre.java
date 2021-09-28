package otus.jdbc.exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {

    private long id;
    private String genre;

    @Override
    public String toString() {
        return "Genre:" +
                "id=" + id +
                ", genre='" + genre + '.';
    }
}
