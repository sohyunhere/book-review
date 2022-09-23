package com.example.bookreview.batch;

import com.example.bookreview.board.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

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
        return stepBuilderFactory.get("JpaPageJob1_step1")
                .<Post, Post>chunk(chunkSize)
                .reader()
                .writer()
                .build();
    }

}
