package com.sorry.buskingbusking.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class UnusedMemberJobConfig {

    @Bean
    public Job unusedMemberJob(JobBuilderFactory jobBuilderFactory) {
        return jobBuilderFactory.get("unusedMemberJob")
                                .preventRestart()
                                .start(unusedMemberStep())
                                .build();
    }

    @Bean
    public Step unusedMemberStep(StepBuilderFactory stepBuilderFactory){
        return stepBuilderFactory.get("unusedMemberStep")
                                .chunk(10)
                                .reader(unusedMemberReader())
                                .processor(unusedMemberProcessor())
                                .writer(unusedMemberWriter())
                                .build();
    }


}
