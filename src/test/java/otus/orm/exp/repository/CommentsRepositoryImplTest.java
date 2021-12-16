package otus.orm.exp.repository;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import otus.orm.exp.entity.mongo.Book;
import otus.orm.exp.entity.mongo.Comment;
import otus.orm.exp.repository.mongo.CommentsRepository;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
class CommentsRepositoryImplTest {

    @Autowired
    private CommentsRepository repository;
    @Autowired
    private MongoOperations mongoOperations;

    private static final String MESSAGE = "book not cool";
    private static final String FIRST = "1";
    private static final String TWO = "2";
    private static final String COMMENT = "4";

    @Test
    public void getCommentTest() {
        Comment comment = repository.findById(FIRST).get();
        Comment expectedComment = mongoOperations.findById(FIRST, Comment.class);
        assertThat(comment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    public void getAllCommentTest() {
        assertThat(repository.findAllByBookId(FIRST)).isNotEmpty()
                .allMatch(c -> StringUtils.isNotBlank(c.getMessage()))
                .allMatch(c -> c.getMessageDate() != null)
                .allMatch(c -> c.getBook() != null);
    }

    @Test
    public void deleteCommentTest() {
        repository.deleteById(TWO);
        assertThat(mongoOperations.findById(TWO, Comment.class)).isNull();
    }

    @Test
    public void saveCommentTest() {
        Comment comment = repository.save(new Comment(COMMENT, MESSAGE, new Date(), mongoOperations.findById(FIRST, Book.class)));
        Comment expectedComment = mongoOperations.findById(COMMENT, Comment.class);
        assertThat(comment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    public void updateCommentTest() {
        Comment comment = repository.save(new Comment(FIRST, MESSAGE, new Date(), mongoOperations.findById(FIRST, Book.class)));
        Comment expectedComment = mongoOperations.findById(FIRST, Comment.class);
        assertEquals(MESSAGE, repository.findById(FIRST).get().getMessage());
        assertThat(comment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

}