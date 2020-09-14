## MY WOOWACOURSE PULLS

매번 우테코 미션을 북마크에 추가하기 귀찮아서 개발한

**우아한 테크코스(woowacourse) PullRequest 미션 목록 서비스**

### 사용법

https://chrome.google.com/webstore/detail/mywoowacoursepulls/dkeblehcoebopgclhhjfbinndbcoboom

1. 위 링크에서 크롬 확장 프로그램을 설치
2. 자신의 닉네임을 검색
3. 해당하는 PullRequest를 클릭해 이동
4. all, open, close 버튼으로 PullRequest 필터링

### 기술 스택?

1. spring boot
2. jpa
3. https(nginx)
4. mariadb(docker)
5. git submodule
6. restTemplate(github api)
7. aws ec2

### TodoList

1. 젠킨스 적용
2. validation(프론트 검색 시 요청 횟수 제한 등...)

### 기능 구현 목록

1. 해당 orgName의 Repository를 조회하는 기능
2. 해당 Repository의 Pull Request를 조회하는 기능
3. database 정보 git submodule에 저장
4. 닉네임으로 모든 Pull Request를 조회하는 기능
5. 모든 Repository, 모든 PullRequest를 가져올 수 있도록 Github Page 파싱 기능
6. 주기적으로 가져올 수 있도록 하는 스케줄러 기능
7. AWS EC2 한국 시간 적용

### 참고 내용

1. UTC 기준(아직 한국 시간이 적용 안됐습니다ㅠ) 오후 6시에 전체 Pull Request들을 update합니다 :)
2. 아직 요청 횟수 제한 기능이 없습니다! 검색 버튼을 조금만 눌러주시길...ㅎㅎ 
3. 피드백(DM, 이메일, 코드리뷰 등등...)은 언제든 환영입니다! 
