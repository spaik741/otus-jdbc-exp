package otus.orm.exp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.Author;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Author, Long> {

    @Override
    List<Author> findAll();


}
