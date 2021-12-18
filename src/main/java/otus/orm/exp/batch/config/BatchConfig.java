package otus.orm.exp.batch.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import otus.orm.exp.entity.h2.BookTo;
import otus.orm.exp.entity.h2.CommentTo;
import otus.orm.exp.entity.mapping.LibraryMapping;
import otus.orm.exp.entity.mongo.Book;
import otus.orm.exp.entity.mongo.Comment;

import java.util.HashMap;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoTemplate mongoTemplate;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, MongoTemplate mongoTemplate) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public Step bookStep(ItemReader<Book> reader, ItemWriter<BookTo> writer,
                         ItemProcessor<Book, BookTo> processor) {
        return stepBuilderFactory.get("bookStep")
                .allowStartIfComplete(true)
                .<Book, BookTo>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step commentStep(ItemReader<Comment> reader, ItemWriter<CommentTo> writer,
                         ItemProcessor<Comment, CommentTo> processor) {
        return stepBuilderFactory.get("commentStep")
                .allowStartIfComplete(true)
                .<Comment, CommentTo>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

   


    @Bean
    public ItemProcessor<Book, BookTo> processorBook() {
        return LibraryMapping::mappingBook;
    }

    @Bean
    public ItemReader<Book> readerBook() {
        return new MongoItemReaderBuilder<Book>()
                .name("bookReader")
                .collection("books")
                .template(mongoTemplate)
                .sorts(new HashMap<>())
                .targetType(Book.class)
                .jsonQuery("{}")
                .build();
    }

    @Bean
    public ItemProcessor<Comment, CommentTo> processorComment() {
        return LibraryMapping::mappingComment;
    }

    @Bean
    public ItemReader<Comment> readerComment() {
        return new MongoItemReaderBuilder<Comment>()
                .name("commentReader")
                .collection("comments")
                .template(mongoTemplate)
                .sorts(new HashMap<>())
                .targetType(Comment.class)
                .jsonQuery("{}")
                .build();
    }

    @Bean
    public Job migrationJob(@Qualifier("bookStep") Step book,
                        @Qualifier("commentStep") Step comment) {
        return jobBuilderFactory
                .get("migrate")
                .incrementer(new RunIdIncrementer())
                .start(book)
                .next(comment)
                .build();
    }
}
