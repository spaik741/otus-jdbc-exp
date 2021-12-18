package otus.orm.exp.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.orm.exp.entity.h2.GenreTo;

import java.util.List;

public interface GenresRepository extends JpaRepository<GenreTo, Long> {

    @Override
    List<GenreTo> findAll();

}
