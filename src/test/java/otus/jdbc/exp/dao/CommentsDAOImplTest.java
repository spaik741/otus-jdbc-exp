package otus.jdbc.exp.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import otus.jdbc.exp.entity.Comment;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import({BooksDAOImpl.class, CommentsDAOImpl.class})
class CommentsDAOImplTest {

    @Autowired
    private CommentsDAO commentsDAO;
    @Autowired
    private BooksDAO booksDAO;

    private static final String MESSAGE = "Could not tear myself away";
    private static final String MESSAGE_2 = "book not cool";
    private static final int LIST_SIZE_1 = 3;
    private static final int LIST_SIZE_2 = 4;
    private static final int LIST_SIZE_3 = 2;

    @Test
    public void getCommentTest() {
        Comment comment = commentsDAO.findById(1);
        assertEquals(MESSAGE, comment.getMessage());
    }

    @Test
    public void getAllCommentTest() {
        assertEquals(LIST_SIZE_1, CollectionUtils.size(commentsDAO.findAll()));
    }

    @Test
    public void deleteCommentTest() {
        commentsDAO.deleteById(1);
        assertEquals(LIST_SIZE_3, commentsDAO.findAll().size());
        assertTrue(CollectionUtils.isEmpty(booksDAO.findById(2).getComment()));
    }

    @Test
    public void saveCommentTest() {
        Comment comment = new Comment(4, MESSAGE_2, new Date());
        commentsDAO.save(comment);
        assertEquals(LIST_SIZE_2, commentsDAO.findAll().size());
    }

    @Test
    public void updateCommentTest() {
        Comment comment = new Comment(1, MESSAGE_2, new Date());
        commentsDAO.save(comment);
        assertEquals(MESSAGE_2, commentsDAO.findById(1).getMessage());
    }

}