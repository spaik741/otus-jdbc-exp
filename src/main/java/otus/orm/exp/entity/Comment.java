package otus.orm.exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private String id;
    @Field(value = "message")
    private String message;
    @Field(value = "date_message")
    private Date messageDate;
    @DBRef
    @Field(value = "id_book")
    private Book book;

    @Override
    public String toString() {
        return "Comment:" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", messageDate=" + messageDate +
                ", book=" + book;
    }
}
