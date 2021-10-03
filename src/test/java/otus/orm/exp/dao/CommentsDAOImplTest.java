package otus.orm.exp.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import otus.orm.exp.entity.Book;
import otus.orm.exp.entity.Comment;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import({BooksDAOImpl.class, CommentsDAOImpl.class})
class CommentsDAOImplTest {

    @Autowired
    private CommentsDAO commentsDAO;
    @Autowired
    private BooksDAO booksDAO;
    @Autowired
    private TestEntityManager em;

    private static final String MESSAGE = "book not cool";
    private static final int LIST_SIZE_1 = 3;
    private static final int LIST_SIZE_2 = 4;
    private static final long FIRST_COMMENT = 1;
    private static final long TWO_COMMENT = 4;

    @Test
    public void getCommentTest() {
        Comment comment = commentsDAO.findById(FIRST_COMMENT);
        Comment expectedComment = em.find(Comment.class, FIRST_COMMENT);
        assertThat(comment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    public void getAllCommentTest() {
        assertEquals(LIST_SIZE_1, CollectionUtils.size(commentsDAO.findAll()));
        assertThat(commentsDAO.findAll()).hasSize(LIST_SIZE_1)
                .allMatch(c -> StringUtils.isNotBlank(c.getMessage()))
                .allMatch(c -> c.getMessageDate() != null)
                .allMatch(c -> c.getBook() != null);
    }

    @Test
    public void deleteCommentTest() {
        assertTrue(commentsDAO.deleteById(FIRST_COMMENT));
        assertThat(em.find(Comment.class, FIRST_COMMENT)).isNull();
    }

    @Test
    public void saveCommentTest() {
        Comment comment = commentsDAO.save(new Comment(TWO_COMMENT, MESSAGE, new Date(), new Book()));
        Comment expectedComment = em.find(Comment.class, TWO_COMMENT);
        assertEquals(LIST_SIZE_2, commentsDAO.findAll().size());
        assertThat(comment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    public void updateCommentTest() {
        Comment comment = commentsDAO.save(new Comment(FIRST_COMMENT, MESSAGE, new Date(), new Book()));
        Comment expectedComment = em.find(Comment.class, FIRST_COMMENT);
        assertEquals(MESSAGE, commentsDAO.findById(1).getMessage());
        assertThat(comment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

}