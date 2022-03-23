package org.kimilguk.shop.controller

import org.kimilguk.shop.common.ApiResponse
import org.kimilguk.shop.common.JWTUtil
import org.kimilguk.shop.common.TokenValidationInterceptor
import org.kimilguk.shop.dto.auth.SigninRequestDto
import org.kimilguk.shop.service.auth.AuthHolderService
import org.kimilguk.shop.service.auth.SigninService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 로그인 API 외부 접근 경로 SigninApiController 클래스
 */
@RequestMapping("/api")
@RestController
class SigninApiController @Autowired constructor(//생성자함수는 클래스를 초기화하는 기능
    private val signinService: SigninService, //val 타입으로 new 키워드 대신 객체 생성(DI 주입)
    private val authHolderService: AuthHolderService
) {
    @PostMapping("/signin") //로그인 요청
    fun signin(@RequestBody signinRequestDto: SigninRequestDto) =
        ApiResponse.ok(signinService.signin(signinRequestDto))
    //토큰이 만료 되었을 때 갱신토큰을 생성하는 요청을 추가한다.(아래)
    @PostMapping("/refresh_token")
    fun refreshToken(@RequestParam("grant_type") grantType: String) : ApiResponse {
        if(grantType != TokenValidationInterceptor.GRANT_TYPE_REFRESH) {
            throw IllegalArgumentException("grant_type 없음")
        }
        return authHolderService.email?.let {
            ApiResponse.ok(JWTUtil.createToken(it))
        }?: throw IllegalArgumentException("사용자 정보 없음")
    }
}