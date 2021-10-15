package otus.orm.exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "authors")
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    private String id;
    @Field(value = "f_name")
    private String firstName;
    @Field(value = "l_name")
    private String lastName;

    @Override
    public String toString() {
        return "Author:" +
                "id=" + id +
                ", firstName='" + firstName +
                ", lastName='" + lastName + '.';
    }
}
