package org.kimilguk.shop.dto.auth

/**
 * JWT 에서 회원 인증 데이터 발행 시 임시 저장할 데이터 SigninResponseDto 클래스
 */
data class SigninResponseDto(
    val token: String,
    val refreshToken: String,
    val userName: String,
    val userId: Long
)
