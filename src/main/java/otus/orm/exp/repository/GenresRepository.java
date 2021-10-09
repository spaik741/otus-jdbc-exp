package otus.orm.exp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.Genre;

import java.util.List;

public interface GenresRepository extends JpaRepository<Genre, Long> {

    @Override
    List<Genre> findAll();

}
