package org.kimilguk.shop.controller

import org.kimilguk.shop.common.ApiResponse
import org.kimilguk.shop.dto.auth.SigninRequestDto
import org.kimilguk.shop.service.auth.SigninService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 로그인 API 외부 접근 경로 SigninApiController 클래스
 */
@RequestMapping("/api")
@RestController
class SigninApiController @Autowired constructor(//생성자함수는 클래스를 초기화하는 기능
    private val signinService: SigninService //val 타입으로 new 키워드 대신 객체 생성(DI 주입)
) {
    @PostMapping("/signin")
    fun signin(@RequestBody signinRequestDto: SigninRequestDto) =
        ApiResponse.ok(signinService.signin(signinRequestDto))
}