package otus.orm.exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "genres")
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    private String id;
    @Field(value = "genre")
    private String genre;

    @Override
    public String toString() {
        return "Genre:" +
                "id=" + id +
                ", genre='" + genre + '.';
    }
}
