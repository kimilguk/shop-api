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
- 구현3: 회원가입 API 코딩 순서 도메인,JPA@레포지토리-Dto클래스-@서비스클래스-@Rest컨트롤러클래스
- 구현4: 회원로그인 API 추가: 토큰 발행 클래스 추가
- 구현5: 

### 20220404(월) 상품 리스트 API 작업
- 안드로이드앱의 스크롤 이벤트를 염두에 두고 스프링부트 페이징 구현
- ProductListRequestDto.kt 추가: 서비스에서 상품 목록출력 시 전송내용이 임시 저장될 데이터 ProductListRequestDto 클래스
- ProductRepository.kt 수정: 상품정보를 읽어오는 인터페이스 정의(카테고리별, 스크롤시 상품 id 기준 전후)
- ProductService.kt 추가: 상품목록을 읽어오는 로직 서비스 클래스
- ProductApiController.kt 수정: API Get 매핑 및 상품목록 서비스를 호출 추가

### 20220328(월) 상품등록 API 추가2 순서(아래)
- ProductRequestDto.kt 추가: 서비스에서 상품 등록요청 시 전송내용이 임시 저장될 데이터 클래스
- ProductCreateService.kt 추가: 상품등록 서비스 로직 함수를 제공하는 클래스
- ProductApiController.kt 추가: 상품등록을 외부에서 접근해는 통로인 RestAPI 클래스
- 부메랑으로 상품입력 테스트: 토큰값은 필수로 사용한다.

### 20220324(목) 상품등록 API 추가1 순서(아래)
- Product.kt 추가: 상품등록에 필요한 JPA용 @Entity 클래스(ProductStatus,BaseEntity 추가)
- ProductRepository.kt 추가:위 product 테이블에 CRUD 쿼리를 실행하는 레포지토리 인터페이스

### 20220323(수) 앱에서 API 서버세션을 사용하지 않고 로그인 정보 유지
- 서버세션의 사용자정보 대신에 클라이언트의 토큰정보로 서버의 ThreadLocal 클래스에서 로그인 정보를 유지한다.
- 작업순서: JWTUtil(JWT 토큰값 검증과 이메일을 추출하는 코드 추가) 
- -> AuthHolderService(클라이언트에서 보낸 토큰정보로 서버의 ThreadLocal 클래스에서 로그인 정보를 유지하는 클래스 생성.) 
- -> TokenValidationInterceptor(스프링의 HandlerInterceptor 를 상속해서 클라이언트 요청 전/후에 자동으로 토큰을 검증하고 로그인 정보저장을 호출하는 클래스 생성) 
- -> WebConfig(스프링에서 위 인터셉터 클래스를 사용하기 위해 등록하는 클래스 생성.) 
- -> SigninApiController(토큰이 만료 되었을 때 갱신토큰을 생성하는 요청 코드를 추가한다.)

### 20220321(월)
- 회원로그인 API 암호 체크부분 수정: BCrypt.checkpw(비암호, DB암호).not() 추가
```java
if(BCrypt.checkpw(signinRequestDto.password, user.password).not()) {
      throw ShopException("API에서 암호가 일치하지 않습니다.")
  }
```

### 20220319(토)
- Json Web Token 토큰발행: com.auth0:java-jwt:3.8.1 의존모듈 추가
- 인증에 필요한 토큰과 리프레시용 토큰을 생성하는 object 싱글톤(프로그램에서 1회만 생성) 클래스 JWTUtil.kt
- JWT 로 회원 인증 데이터 요청 시 임시 저장할 데이터 SigninRequestDto 클래스
- JWT 에서 회원 인증 데이터 발행 시 임시 저장할 데이터 SigninResponseDto 클래스
- JWT 를 사용한 로그인 로직 서비스 구현 SigninService 클래스
- 로그인 API 외부 접근 경로 SigninApiController 클래스

### 20220318(금)
- Dto, Service, Controller 코딩

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