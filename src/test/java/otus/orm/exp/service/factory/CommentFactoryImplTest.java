package otus.orm.exp.service.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Comment;
import otus.orm.exp.service.BooksService;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CommentFactoryImplTest {

    private static final Long LONG_VAL = 1L;

    @MockBean
    private BooksService bookService;
    private CommentFactory commentFactory;

    @BeforeEach
    void init() {
        bookService = mock(BooksService.class);
        commentFactory = new CommentFactoryImpl(bookService);
    }

    @Test
    public void testCreateComment() {
        given(bookService.getBookById(LONG_VAL)).willReturn(Optional.of(new Book()));
        Optional<Comment> comment = commentFactory.createComment("message", new Date(), LONG_VAL);
        assertThat(comment.get()).isNotNull().matches(c -> c.getMessage().equals("message"));
    }

}