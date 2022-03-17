### 책 [코틀린으로 쇼핑몰앱 만들기] 소스를 분석 하고 있습니다.
- 기술참조: 코틀린으로 쇼핑몰 앱 만들기(터닝포인트출판사:이현석 저자)
- 소스참조(스프링부트API): https://github.com/benimario/parayo-api
- 소스참조(안드로이드앱): https://github.com/benimario/parayo-android
- 신규작업(스프링부트API) : https://github.com/kimilguk/shop-api.git
- 신규작업(안드로이드앱) : https://github.com/kimilguk/shop-app.git
- 인텔리J 에서 깃 암호 저장하지 않게 설정(아래) 
- https://stackoverflow.com/questions/28142361/change-remote-repository-credentials-authentication-on-intellij-idea-14
- 헤로쿠용 빌드 파일 고정함. version '1.0-SNAPSHOT' 수정.
- 작업기간: 00일 ( 20220000 ~ 20220000 )

### 학습목차

### 스프링부트 API 프로젝트에서 구현된 내역(아래)
- 개발툴: 인텔리J 커뮤니티, 빌드툴버전: 그래들7.1, 스프링부트버전: 2.4.9, 자바버전: 오픈 JDK8, 코틀린 1.5.10
- 사용된 기술: 하이버네이트 스프링 JPA, H2(메모리) postgreSQL(RDBS) 데이터베이스, HikariPool(스프링부트에 내장된 기본 DB 커넥션), 스프링 시큐리티(DB) + OAuth2(네이버외부API) + 스프링세션
- 구현1: 코틀린 JUnit 테스트, Hello 코틀린 RestAPI 출력, 공통 에러 처리
- 구현2: application.yml 야물 설정파일에서 h2 메모리 DB 사용 추가 후 http://localhost:8080/h2-console 접속가능
- 구현3: 회원가입 API 코딩 추가
- 구현4: 
- 구현5: 

### 20220317(목)
- 회원가입에 필요한 JPA용 @Entity 클래스 추가: Users 
- 회원가입 JPA 레포지토리 추가
- 회원가입 @Service 추가

### 20220313(일)
- 그래들(외부모듈)설정파일: build.gradle 확인 
- VCS->Import Into Version Control->Create Git Repository 로 로컬 깃 생성
- 깃허브에 레포지토리 생성 후 깃 주소 복사 (https://github.com/kimilguk/shop-api.git)
- commit 과 푸시 모두 실행
- 인텔리J 커뮤니티(무료)버전은 UI로 스프링 프로젝트를 지원하지 않지만, build.gradle 플러그인 추가로 스프링부트를 사용할 수 있다.
- 헤로쿠와 연동 Procfile 메이븐에서 그래들빌드용으로 변경(아래).
- web: java -Dserver.port=$PORT $JAVA_OPTS -jar build/libs/shop1.0-SNAPSHOT.jar