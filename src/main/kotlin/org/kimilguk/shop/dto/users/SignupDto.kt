package org.kimilguk.shop.dto.users

/**
 * 클래스간 데이터를 전송할 때 임시로 저장할 데이터 클래스
 */
data class SignupDto(
    val email: String,
    val name: String,
    val password: String
)
