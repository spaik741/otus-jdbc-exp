package otus.jdbc.exp.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import otus.jdbc.exp.entity.Comment;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({BooksDAOImpl.class, CommentsDAOImpl.class})
class CommentsDAOImplTest {

    @Autowired
    private CommentsDAO commentsDAO;
    @Autowired
    private BooksDAO booksDAO;

    private static final String MESSAGE = "book cool";
    private static final String MESSAGE_2 = "book not cool";
    private static final int LIST_SIZE_1 = 3;
    private static final int LIST_SIZE_2 = 4;
    private static final int LIST_SIZE_3 = 2;

    @Test
    public void getCommentTest() {
        Comment comment = commentsDAO.findById(1);
        assertEquals(comment.getMessage(), MESSAGE);
    }

    @Test
    public void getAllCommentTest() {
        assertEquals(CollectionUtils.size(commentsDAO.findAll()), LIST_SIZE_1);
    }

    @Test
    public void deleteCommentTest() {
        commentsDAO.deleteById(1);
        assertEquals(commentsDAO.findAll().size(), LIST_SIZE_3);
    }

    @Test
    public void deleteBookTest() {
        booksDAO.deleteById(1);
        assertEquals(commentsDAO.findAll().size(), LIST_SIZE_3);
    }

    @Test
    public void saveCommentTest() {
        Comment comment = new Comment(4, MESSAGE_2, new Date());
        commentsDAO.save(comment);
        assertEquals(commentsDAO.findAll().size(), LIST_SIZE_2);
    }

    @Test
    public void updateCommentTest() {
        Comment comment = new Comment(1, MESSAGE_2, new Date());
        commentsDAO.save(comment);
        assertEquals(commentsDAO.findById(1).getMessage(), MESSAGE_2);
    }

}