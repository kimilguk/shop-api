package org.kimilguk.shop.common

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import java.util.*

/**
 * 인증에 필요한 토큰과 리프레시용 토큰을 생성하는 object 싱글톤(프로그램에서 1회만 생성) JWTUtil 클래스
 */
object JWTUtil {
    //const val 형은 컴파일 시 메모리에 할당되는 상수로 주로 싱글톤에서 사용 자바의 static final. 기본형과 문자열인 String 만 입력가능
    //val 형은 런타임 시 메모리에 할당되는 상수로 자바의 final. 상수: 선언된(값이 할당된) 이후 변수의 값을 바꿀 수 없다
    //var 형은 런타임 시 메모리에 할당되는 변수로 자바의 일반변수와 같다. 선언된 이후 변수값을 바꿀 수 있다.
    private const val ISSUER = "Shop"
    private const val SUBJECT = "Auth"
    private const val EXPIRE_TIME = 60L * 60 * 2 * 1000 //2시간 단위 밀리초
    private val secret = "your-secret"
    private val algorithm: Algorithm = Algorithm.HMAC256(secret)
    fun createToken(email: String) = JWT.create()
        .withIssuer(ISSUER)
        .withSubject(SUBJECT)
        .withIssuedAt(Date())
        .withExpiresAt(Date(Date().time+ EXPIRE_TIME))
        .withClaim(JWTClaims.EMAIL, email)
        .sign(algorithm)
    private val refreshSecret = "your-refresh-secret"
    private val refreshAlgorithm: Algorithm = Algorithm.HMAC256(refreshSecret)
    fun createRefreshToken(email: String) = JWT.create()
        .withIssuer(ISSUER)
        .withSubject(SUBJECT)
        .withIssuedAt(Date())
        .withExpiresAt(Date(Date().time+ EXPIRE_TIME))
        .withClaim(JWTClaims.EMAIL, email)
        .sign(refreshAlgorithm)

    object JWTClaims {
        const val EMAIL = "email"
    }
    //authHolderService 인증값 저장 전에 JWT 토큰값 검증과 이메일을 추출하는 코드
    fun verify(token: String): DecodedJWT = //토큰값 검증 함수
        JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build()
            .verify(token)
    fun verifyRefresh(token: String): DecodedJWT = //재생 토큰값 검증 함수
        JWT.require(refreshAlgorithm)
            .withIssuer(ISSUER)
            .build()
            .verify(token)
    fun extractEmail(jwt: DecodedJWT): String =
        jwt.getClaim(JWTClaims.EMAIL).asString() //로그인 시 생성된 토큰에서 이메일 반환
}