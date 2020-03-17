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

