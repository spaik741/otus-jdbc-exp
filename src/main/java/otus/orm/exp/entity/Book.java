package otus.orm.exp.entity;

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
@NamedEntityGraph(name = "BookGraph",
        attributeNodes = {
                @NamedAttributeNode("genre"),
                @NamedAttributeNode("author")
        })
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "id_author")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Author author;
    @JoinColumn(name = "id_genre")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Genre genre;

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre;
    }
}
