package otus.orm.exp.entity.h2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
public class AuthorTo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "f_name")
    private String firstName;
    @Column(name = "l_name")
    private String lastName;

    @Override
    public String toString() {
        return "Author:" +
                "id=" + id +
                ", firstName='" + firstName +
                ", lastName='" + lastName +'.';
    }
}
