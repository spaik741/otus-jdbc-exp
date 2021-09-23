package otus.jdbc.exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

    private long id;
    private String name;

    @Override
    public String toString() {
        return "Author:" +
                "id=" + id +
                ", name='" + name + '.';
    }
}
