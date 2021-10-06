package otus.orm.exp.service.factory;

import otus.orm.exp.entity.Book;

import java.util.Optional;

public interface BookFactory {
    Optional<Book> createBook(String name, long idAuthor, long idGenre);
}
