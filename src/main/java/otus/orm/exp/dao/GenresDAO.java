package otus.orm.exp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.Genre;

import java.util.List;

public interface GenresDAO extends JpaRepository<Genre, Long> {

    @Override
    List<Genre> findAll();

}
