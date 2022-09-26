package com.example.bookreview.batch;

import com.example.bookreview.batch.model.PostCount;
import com.example.bookreview.board.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JpaPageJob1 {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private int chunkSize = 10;

    @Bean
    public Job JpaPageJob1_batchBuild(){
        return jobBuilderFactory.get("JpaPageJob1")
                .start(JpaPageJob1_step1()).build();
    }

    @Bean
    public Step JpaPageJob1_step1(){
        return stepBuilderFactory
                .get("JpaPageJob1_step1")
                .<Post, PostCount>chunk(chunkSize)
                .reader(JpaPageJob1_dbItemReader())
                .processor(JpaPageJob1_processor())
                .writer(JpaPageJob1_dbItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Post> JpaPageJob1_dbItemReader(){
        return new JpaPagingItemReaderBuilder<Post>()
                .name("JpaPageJob1_dbItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT category_id, count(category_id) from Post where writtenDate < '2022-09-23' AND writtenDate > '2022-09-16' GROUP BY category_id")
                .build();
    }

    private ItemProcessor<Object, PostCount> JpaPageJob1_processor(){
        return items -> {
            Object[] objects = (Object[]) items;
            Iterator<Object> iterator = Arrays.stream(objects).iterator();
            List<String> list = new ArrayList<>();

            while(iterator.hasNext()){
                String value = iterator.next().toString();
                list.add(value);
            }

            return PostCount.builder()
                    .category_Id(list.get(0))
                    .countPost(Integer.parseInt(list.get(1)))
                    .time(LocalDate.now())
                    .build();
        };
    }
    public JpaItemWriter<PostCount> JpaPageJob1_dbItemWriter(){
        JpaItemWriter<PostCount> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;

    }
}
