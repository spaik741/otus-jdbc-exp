package otus.orm.exp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.Author;

import java.util.List;

public interface AuthorsDAO extends JpaRepository<Author, Long> {

    @Override
    List<Author> findAll();


}
