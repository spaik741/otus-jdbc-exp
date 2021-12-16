package otus.orm.exp.service.factory;

import otus.orm.exp.entity.mongo.Book;

import java.util.Optional;

public interface BookFactory {
    Optional<Book> createBook(String name, String idAuthor, String idGenre);
}
