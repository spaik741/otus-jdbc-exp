package otus.orm.exp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "date_message", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date messageDate;
    @JoinColumn(name = "id_book")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
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
