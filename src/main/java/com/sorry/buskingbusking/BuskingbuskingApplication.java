package com.sorry.buskingbusking;

import com.sorry.buskingbusking.repository.CommonCodeRepository;
import com.sorry.buskingbusking.repository.MemberRepository;
import com.sorry.buskingbusking.repository.NoticeRepository;
import com.sorry.buskingbusking.repository.PerformanceRepository;
import com.sorry.buskingbusking.domain.CommonCode;
import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.Notice;
import com.sorry.buskingbusking.domain.Performance;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


@SpringBootApplication
@EnableJpaAuditing
@EnableBatchProcessing
public class BuskingbuskingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuskingbuskingApplication.class, args);
	}


	//@Bean
	public CommandLineRunner runner(MemberRepository memberRepository) throws Exception{
		return (args)->{
					IntStream.rangeClosed(1, 50).forEach(index ->
							memberRepository.save(
								Member.builder()
										.email("wivipp"+index+"@naver.com")
											.nickName("nick_"+index)
										.mobile("010714671"+index)
										.delYn("N")
										.password(index + "")
										.build()
							)
					); //for each 종료
		};
	}


	//@Bean
	public CommandLineRunner runner2(NoticeRepository noticeRepository,MemberRepository memberRepository) throws Exception{
		return (args)->{
			Member writer = Member.builder()
					.email("wivipp39@naver.com")
					.nickName("nickName")
					.mobile("01071467182")
					.delYn("N")
					.password("1234")
					.build();
			memberRepository.save(writer);

			IntStream.rangeClosed(1, 40).forEach(index ->{
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

	//@Bean
	public CommandLineRunner runner3(PerformanceRepository performanceRepository, MemberRepository memberRepository) throws Exception{
		return (args)->{
			Member writer = Member.builder()
					.email("wivipp39@naver.com")
					.nickName("nickName")
					.mobile("01071467182")
					.delYn("N")
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

	//@Bean
	public CommandLineRunner runner4(CommonCodeRepository commonCodeRepository) throws Exception{
		return (args)->{
			IntStream.rangeClosed(1, 10).forEach(index ->{
				commonCodeRepository.save(
						CommonCode.builder()
								.codeName("코드값"+index)
								.codeDesc("코드설명"+index)
								.regDt(LocalDateTime.now())
								.updDt(LocalDateTime.now())
								.build()
				);
			}); //for each 종료
		};
	}

	@Bean
	public CommandLineRunner createCode(CommonCodeRepository commonCodeRepository) throws Exception{
		return (args)->{

				String[] locArray = {"홍대","건대","대학로","신촌","한강공원"};
				List<String> locList = new ArrayList<>(Arrays.asList(locArray));

				locList.stream().forEach(arr->{
					CommonCode code = CommonCode.builder().codeName("LocCode")
															.codeDesc(arr)
															.regDt(LocalDateTime.now())
															.build();
					commonCodeRepository.save(code);

				});

				String[] genreArray = {"노래","랩","춤","마술","행위예술","피아노"};
				List<String> genreList = new ArrayList<>(Arrays.asList(genreArray));

				genreList.stream().forEach(arr->{
					CommonCode code = CommonCode.builder().codeName("GenreCode")
														.codeDesc(arr)
														.regDt(LocalDateTime.now())
														.build();
					commonCodeRepository.save(code);
				});

		};
	}

}
