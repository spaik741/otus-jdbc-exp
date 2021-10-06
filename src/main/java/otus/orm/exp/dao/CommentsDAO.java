package otus.orm.exp.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Comment;

import java.util.List;

public interface CommentsDAO extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBook(Book book);

}
