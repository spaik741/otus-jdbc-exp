package otus.jdbc.exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private long id;
    private String name;
    private long idAuthor;
    private long idGenre;

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + idAuthor +
                ", genre=" + idGenre + ".";
    }
}
