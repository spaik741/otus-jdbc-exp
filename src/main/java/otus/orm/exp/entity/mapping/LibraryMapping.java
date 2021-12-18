package otus.orm.exp.entity.mapping;

import otus.orm.exp.entity.h2.AuthorTo;
import otus.orm.exp.entity.h2.BookTo;
import otus.orm.exp.entity.h2.CommentTo;
import otus.orm.exp.entity.h2.GenreTo;
import otus.orm.exp.entity.mongo.Author;
import otus.orm.exp.entity.mongo.Book;
import otus.orm.exp.entity.mongo.Comment;
import otus.orm.exp.entity.mongo.Genre;

public class LibraryMapping {

    public static BookTo mappingBook(Book book) {
        return new BookTo(Long.parseLong(book.getId()),
                book.getName(),
                mappingAuthor(book.getAuthor()),
                mappingGenre(book.getGenre()));
    }

    public static AuthorTo mappingAuthor(Author author) {
        return new AuthorTo(Long.parseLong(author.getId()),
                author.getFirstName(),
                author.getLastName());
    }

    public static GenreTo mappingGenre(Genre genre) {
        return new GenreTo(Long.parseLong(genre.getId()),
                genre.getGenre());
    }

    public static CommentTo mappingComment(Comment comment) {
        return new CommentTo(Long.parseLong(comment.getId()),
                comment.getMessage(),
                comment.getMessageDate(),
                mappingBook(comment.getBook()));
    }
}
