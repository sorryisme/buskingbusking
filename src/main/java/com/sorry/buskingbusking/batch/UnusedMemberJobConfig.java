package com.sorry.buskingbusking.batch;

import com.sorry.buskingbusking.batch.readers.QueueItemReader;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.UserStatus;
import com.sorry.buskingbusking.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Configuration
public class UnusedMemberJobConfig {

    private MemberRepository memberRepository;

    @Bean
    public Job unusedMemberJob(JobBuilderFactory jobBuilderFactory, Step unusedJobStep) {
        return jobBuilderFactory.get("unusedMemberJob")
                                .preventRestart()
                                .start(unusedJobStep)
                                .build();
    }

    @Bean
    public Step unusedMemberStep(StepBuilderFactory stepBuilderFactory){
        return stepBuilderFactory.get("unusedMemberStep")
                                .<Member,Member>chunk(10)
                                .reader(unusedMemberReader())
                                .processor(unusedMemberProcessor())
                                .writer(unusedMemberWriter())
                                .build();
    }

    @Bean
    @StepScope
    public QueueItemReader<Member> unusedMemberReader(){
        List<Member> oldUsers =
                    memberRepository.findByModifiedDateBeforeAndUserStatusEquals(LocalDateTime.now().minusYears(1), UserStatus.USE);
        return new QueueItemReader<>(oldUsers);
    }

    public ItemProcessor<Member,Member> unusedMemberProcessor(){
        return new ItemProcessor<Member,Member>(){
            @Override
            public Member process(Member item) throws Exception {
                return item.setUnused();
            }
        };
    }

    public ItemWriter<Member> unusedMemberWriter(){
        return ((List<? extends Member> members) -> memberRepository.saveAll(members));
    }

}
