package org.kimilguk.shop.dto.auth

/**
 * JWT 로 회원 인증 데이터 요청 시 임시 저장할 데이터 SigninRequestDto 클래스
 */
data class SigninRequestDto(
    val email: String, //회원 email
    val password: String//회원 암호
)
