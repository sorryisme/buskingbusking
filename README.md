# 작업내역

1. Mybatis -> JPA로 변경
2. 서버 Validation 추가 (@Valid를 통한 처리)
3. 공통 에러 페이지 처리(@ControllerAdvice적용)
4. ControllerAdvice를 통한 공통 세션정보 공유
5. 어드민 페이지 추가
6. 404 에러 페이지 추가 (DispathcherServlet initparam 등록)

# 이슈 및 트러블슈팅
1. 윈도우 개발환경에서 파일저장 시 오류발생
   - 설정파일 내 basedir 지정하여 처리 https://m.blog.naver.com/PostView.nhn?blogId=goddes4&logNo=220296020812&proxyReferer=https:%2F%2Fwww.google.com%2F