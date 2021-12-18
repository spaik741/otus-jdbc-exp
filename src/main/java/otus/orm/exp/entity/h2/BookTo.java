package otus.orm.exp.entity.h2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "BookGraph",
        attributeNodes = {
                @NamedAttributeNode("genreTo"),
                @NamedAttributeNode("authorTo")
        })
public class BookTo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "id_author")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private AuthorTo authorTo;
    @JoinColumn(name = "id_genre")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private GenreTo genreTo;

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + authorTo +
                ", genre=" + genreTo;
    }
}
