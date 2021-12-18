package otus.orm.exp.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import otus.orm.exp.entity.h2.AuthorTo;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<AuthorTo, Long> {

    @Override
    List<AuthorTo> findAll();


}
