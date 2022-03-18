package org.kimilguk.shop.controller

import org.kimilguk.shop.common.ApiResponse
import org.kimilguk.shop.service.users.SignupService
import org.kimilguk.shop.dto.users.SignupDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 외부에서 접근해는 통로인 RestAPI 컨트롤러 클래스
 */
@RequestMapping("/api")//초기 외부 접근 경로
@RestController
class UsersApiController  @Autowired constructor(
    private val signupService: SignupService //생성자로 초기 객체 생성(DI 주입)
){
    @PostMapping("/users")//HTTP 의 POST 메소드 전송상태로 접근하는 액션 처리
    fun signup(@RequestBody request: SignupDto) = //return 대신 = 사용해서 반환값 사용
        ApiResponse.ok(signupService.signup(request))//데이터를 전송받는 ok 함수 실행
}