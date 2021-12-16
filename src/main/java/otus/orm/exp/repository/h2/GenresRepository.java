package otus.orm.exp.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.h2.Genre;

import java.util.List;

public interface GenresRepository extends JpaRepository<Genre, Long> {

    @Override
    List<Genre> findAll();

}
