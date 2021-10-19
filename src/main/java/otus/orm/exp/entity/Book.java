package otus.orm.exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data

@NoArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    @Field(value = "name")
    private String name;
    @Field(value = "id_author")
    private Author author;
    @Field(value = "id_genre")
    private Genre genre;
    @DBRef
    @Field(value = "comments")
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", comments=" + comments +
                '}';
    }

    public Book(String id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(String id, String name, Author author, Genre genre, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }
}
