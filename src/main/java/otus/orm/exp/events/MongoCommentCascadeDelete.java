package otus.orm.exp.events;

import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.orm.exp.entity.mongo.Book;
import otus.orm.exp.service.CommentService;

@Component
public class MongoCommentCascadeDelete extends AbstractMongoEventListener<Book> {

    private final CommentService commentService;

    public MongoCommentCascadeDelete(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        val source = event.getSource();
        String id = source.get("_id").toString();
        commentService.deleteCommentByBookId(id);
    }
}
