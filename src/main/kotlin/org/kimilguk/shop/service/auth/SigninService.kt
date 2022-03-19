package org.kimilguk.shop.service.auth

import org.kimilguk.shop.common.JWTUtil
import org.kimilguk.shop.common.ShopException
import org.kimilguk.shop.domain.users.Users
import org.kimilguk.shop.domain.users.UsersRepository
import org.kimilguk.shop.dto.auth.SigninRequestDto
import org.kimilguk.shop.dto.auth.SigninResponseDto
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

/**
 * JWT 를 사용한 로그인 로직 서비스 구현 SigninService 클래스
 */
@Service
class SigninService @Autowired constructor(//생성자함수는 클래스를 초기화하는 기능
    private val usersRepository: UsersRepository //val 타입으로 new 키워드 대신 객체 생성(DI 주입)
) {
    fun signin(signinRequestDto: SigninRequestDto): SigninResponseDto {
        val user = usersRepository.findByEmail(signinRequestDto.email.toLowerCase())
            ?: throw ShopException("이메일이 일치하지 않습니다.")//엘비스 연산자 사용해서 예외 처리
        if(BCrypt.checkpw(signinRequestDto.password, user.password)) {
            throw ShopException("암호가 일치하지 않습니다.")
        }
        return responseWithTokens(user)
    }

    private fun responseWithTokens(user: Users): SigninResponseDto {
        //TODO("인증된 회원 토큰값 반환")
        return user.id?.let {
            SigninResponseDto(
                JWTUtil.createToken(user.email),
                JWTUtil.createRefreshToken(user.email),
                user.name,
                it
            )
        }?: throw IllegalStateException("user.id 없음")
    }

}