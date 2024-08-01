package com.project.tondeuse.batch;

import com.project.tondeuse.entites.Tondeuse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public BatchConfiguration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }


    
    

    @Bean
    public Job tondeuseJob(TondeuseItemReader reader, TondeuseItemProcessor processor, TondeuseItemWriter writer) {
        Step step = new StepBuilder("tondeuseStep", jobRepository)
                .<Tondeuse, Tondeuse>chunk(1, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

        return new JobBuilder("tondeuseJob", jobRepository)
                .start(step)
                .build();
    }
}
