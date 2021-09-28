package otus.jdbc.exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "library-books-graph",
        attributeNodes = {@NamedAttributeNode("author"),
                @NamedAttributeNode("genre"),
                @NamedAttributeNode("comment")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "id_author")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Author author;
    @JoinColumn(name = "id_genre")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Genre genre;
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "books_comments",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_comment"))
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comment;

    public Book(Long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", genre=" + comment;
    }
}
