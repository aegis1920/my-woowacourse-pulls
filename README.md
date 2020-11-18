## MY WOOWACOURSE PULLS

매번 우테코 미션을 북마크에 추가하기 귀찮아서 개발한 **우아한 테크코스(woowacourse) PullRequest 미션 목록 서비스**

### 사용법

<img src="https://i.ibb.co/BKSTH0k/main.png" width="300" alt="architecture" />

1. [LINK](https://chrome.google.com/webstore/detail/mywoowacoursepulls/dkeblehcoebopgclhhjfbinndbcoboom)에서 크롬 확장 프로그램을 설치
2. 자신의 닉네임을 검색
3. 해당하는 PullRequest를 클릭해 이동
4. All, Open, Closed 버튼으로 PullRequest 필터링
5. 매일 오후 6시에 전체 Pull Request들을 update합니다

### 개발기

[MY WOOWACOURSE PULLS 개발기](https://aegis1920.github.io/wiki/my-woowacourse-pulls-review.html)

### 아키텍처

<img src="https://i.ibb.co/WzrJXWm/arch.png" width="500" alt="architecture" />

### 기술 스택

Main : Spring Boot 2.x, JPA, MariaDB, RestTemplate


### 기능 구현 목록

- Organization의 이름으로 Repository를 조회하는 기능
- Repository의 이름으로 Pull Request를 조회하는 기능
- database 정보 git submodule에 저장
- 닉네임으로 모든 Pull Request를 조회하는 기능
- Github Page 파싱 기능
- 주기적으로 가져올 수 있도록 하는 스케줄러 기능
- AWS EC2 한국 시간 적용
- `Nginx` 에 `https` 설정
- 도메인 호스팅 구입 및 적용


### TodoList 및 이슈

1. 동명이인 검색 시 모두 검색되는 문제
2. 배포 자동화 젠킨스 적용
3. 시간순 필터링
4. validation(요청 횟수 제한, DTO 등등)
5. 로깅 슬랙 알림

### 피드백(DM, 이메일, 코드리뷰 등등...)은 언제든 환영입니다! 
