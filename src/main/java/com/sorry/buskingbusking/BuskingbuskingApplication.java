package com.sorry.buskingbusking;

import com.sorry.buskingbusking.Repository.MemberRepository;
import com.sorry.buskingbusking.domain.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.stream.IntStream;


@SpringBootApplication
public class BuskingbuskingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuskingbuskingApplication.class, args);
	}


	@Bean
	public CommandLineRunner runner(MemberRepository memberRepository) throws Exception{
		return (args)->{
					IntStream.rangeClosed(1, 50).forEach(index ->
							memberRepository.save(
								Member.builder()
										.email("wivipp"+index+"@naver.com")
										.mobile("010714671"+index)
										.delYn("N")
										.regDt(LocalDateTime.now())
										.password(index + "")
										.build()
							)
					); //for each 종료
		};
	}
}
