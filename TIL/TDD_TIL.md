## @WebMvcTest

- mvc를 위한 테스트

- 애노테이션 사용 시 MVC 관련설정만 로드되어 @SpringBootTest보다 가볍게 테스트 가능

- 로드 되는 MVC 설정 애노테이션은 다음과 같음

  - @Controller
  - @ControllerAdvice
  - @JsonComponent
  - Filter
  - WebMvcConfigurer
  - HandlerMethodArgumentResolver

- MockMvc를 주입하여 서버를 실행시키지 않고 테스트가 가능

- Service는 @WebMvcTest 적용대상이 아니기 때문에 @MockBean을 통해서 가짜 객체를 구현

  ```java
  @MockBean
  private MemberService memberService;
  ```

### MockBean

- given을 사용하여 실행에 대한 반환값을 미리 정의한다 [ import static org.mockito.BDDMockito.given ]

  ```
  Member member = new Member();       given(memberService.getMemberList()).willReturn(Collections.singletonList(member));
  ```




### Mock MVC

- MockMvc를 사용하면 해당 URL의 상태값, 반환값에 대한 테스트 수행

  ```java
  @Autowired
  MockMvc mvc;
  
  mvc.perform(get("book"))
  	.andExpect(status().isOK)
  	.andExpect(view().name("member/memberList"))
  	.andExpect(model().attributeExists("memberList"))
  	.andExpect(model().attribute("memberList",contatins(member))
  ```

  - andExpect(status.isOk()) : 상태값이 200인지 테스트
  - andExpect(view().name("member/memberList")) : 반환되는 뷰 이름 테스트
  - andExpect(model().attributeExist("memberList")) : 모델 프로퍼티 중에 memberList가 있는지 확인
  - andExpect(model().attribute("memberList",contains(member))) : memberList 프로퍼티에 book객체가 있는지 확인 
    - 

- MemberController.java

  ```java
  @GetMapping("/member/getList")
  public String memberGetList(Model model){
  	model.addAttribute("memberList", memberService.getMemberList());
  	return "/member/memberList";
  }
  ```

  

## @DataJpaTest

- @DataJpaTest 어노테이션은 JPA 관련 테스트 설정만 로드

- 테스트가 끝날 때마다 사용한 데이터를 롤백

  ```java
  @RunWith(SpringRunner.class)
  @DataJpaTest
  @ActiveProfiles("...")
  @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
  public CLass JpaTest{
  
  }
  ```

  - AutoConfigureTestDatabase 기본 값이 Replace.Any인 경우 내장된 데이터소스를 사용
  - Replace.NONE의 경우 ActivateProfiles에 설정한 데이터소스가 적용



### @[RunWith(SpringRunner.class)](https://eminentstar.github.io/2017/07/23/about-junit-and-test.html)

- Junit 프레임워크에서 테스트 메소드의 실행을 담당하고 있는 클래스를 Test runner라고 호칭
- @Runwith 애노테이션은 BlockJUnit4ClassRunner 대신 지정된 클래스를 이용해 테스트 메소드를 수행
  - 일종의 Junit프레임워크 확장



### TestEntityManager

- EntityManagerFactory를 파라미터로 받아 객체를 생성한다.

- @Autowired 하면 자동으로 Bean에서 생성받아서 생성될 것이다.

  ```java
  private final EntityManagerFactory entityManagerFactory;
  
  	public TestEntityManager(EntityManagerFactory entityManagerFactory) {
  		Assert.notNull(entityManagerFactory, "EntityManagerFactory must not be null");
  		this.entityManagerFactory = entityManagerFactory;
  	}
  
  ```

- TestEntityFactory는 @DataJpaTest 애노테이션을 있어야 주입된다.



### Thymeleaf

```java
<tr th:each= "member : ${memberList}">
	<td th:text="${member.id}"></td>
</tr>
```




### [CommandLineRunner](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/CommandLineRunner.html)

- 어플리케이션과 동시에 객체를 생성할 때 사용
- @Order 어노테이션으로 순서를 정할 수 있으며 ApplicationRunner도 비슷하게 동작한다
  - ApplicationRunner는 람다에서 ApplicationArguments를 받는다.
  - CommandLineRunner는 람다에서 String 인자를 받는다.
- 두 인터페이스 모두 Funcitonal Interface이다.

````java
@Bean
	public CommandLineRunner runner(JpaRepository memberRepository) throws Exception{
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
````

- memberRepository < - > JpaRepository 객체를 사용해도 DI가 가능한 이유는?



### [Thymeleaf LocalDateTime](https://gist.github.com/romach/337a788b5303454e08811b80767f55f1)

- Thymeleaf에서 LocalDateTime Format을 사용하기 위해서 라이브러리 추가

```java
dependencies {
    compile('org.springframework.boot:spring-boot-starter-thymeleaf')
    compile('org.thymeleaf.extras:thymeleaf-extras-java8time')
}
```

```java
# template.html
<tbody>
    <th:block th:each="campaign : ${campaigns}">
        <tr>
            <td th:text="${#temporals.format(campaign.startTime(), 'yyyy-MM-dd HH:mm:ss')}">startTime</td>
        </tr>
    </th:block>
</tbody>
ble>
```





