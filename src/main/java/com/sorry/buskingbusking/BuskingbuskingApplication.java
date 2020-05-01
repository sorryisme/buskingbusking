package com.sorry.buskingbusking;

import com.sorry.buskingbusking.Repository.MemberRepository;
import com.sorry.buskingbusking.Repository.NoticeRepository;
import com.sorry.buskingbusking.Repository.PerformanceRepository;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.Performance;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.stream.IntStream;


@SpringBootApplication
public class BuskingbuskingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuskingbuskingApplication.class, args);
	}


	/*@Bean*/
	public CommandLineRunner runner(MemberRepository memberRepository) throws Exception{
		return (args)->{
					IntStream.rangeClosed(1, 50).forEach(index ->
							memberRepository.save(
								Member.builder()
										.email("wivipp"+index+"@naver.com")
										.nickName("nick_"+index)
										.mobile("010714671"+index)
										.delYn("N")
										.regDt(LocalDateTime.now())
										.password(index + "")
										.build()
							)
					); //for each 종료
		};
	}


//	@Bean
	public CommandLineRunner runner2(NoticeRepository noticeRepository,MemberRepository memberRepository) throws Exception{
		return (args)->{
			Member writer = Member.builder()
					.email("wivipp39@naver.com")
					.nickName("nickName")
					.mobile("01071467182")
					.delYn("N")
					.regDt(LocalDateTime.now())
					.password("1234")
					.build();
			memberRepository.save(writer);

			IntStream.rangeClosed(1, 10).forEach(index ->{
				noticeRepository.save(
						Notice.builder()
							.member(writer)
							.noticeTitle("공지사항 제목 " + index)
							.noticeContents("공지사항 내용 " + index)
							.regDt(LocalDateTime.now())
							.viewCnt(0)
							.build()
					);
			}); //for each 종료
		};
	}

	@Bean
	public CommandLineRunner runner3(PerformanceRepository performanceRepository, MemberRepository memberRepository) throws Exception{
		return (args)->{
			Member writer = Member.builder()
					.email("wivipp39@naver.com")
					.nickName("nickName")
					.mobile("01071467182")
					.delYn("N")
					.regDt(LocalDateTime.now())
					.password("1234")
					.build();
			memberRepository.save(writer);

			IntStream.rangeClosed(1, 10).forEach(index ->{
				performanceRepository.save(
						Performance.builder()
								.member(writer)
								.performanceName("공연" + index)
								.performanceGenre("음악")
								.performanceDate("20200429")
								.performanceLocation("강남")
								.performanceDesc("설명")
								.performanceRemark("비고")
								.viewCnt(0)
								.regDt(LocalDateTime.now())
								.build()
				);
			}); //for each 종료
		};
	}

}
