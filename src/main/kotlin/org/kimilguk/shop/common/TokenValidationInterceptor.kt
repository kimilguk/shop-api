package org.kimilguk.shop.common

import org.kimilguk.shop.service.auth.AuthHolderService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 스프링의 HandlerInterceptor 를 상속해서 클라이언트 요청 전/후에 자동으로 토큰을 검증하고 로그인 로그인 정보저장을 호출
 * ThreadLocal 클래스에 저장해서 세션대신 로그인 정보를 유지하게 처리한다.
 * 이 인터셉터 클래스의 실행은 설정클래스에 등록해서 실행한다.
 */
@Component //스프링빈으로 사용 한다고 명시.
class TokenValidationInterceptor @Autowired constructor(
    private val authHolderService: AuthHolderService
) : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(this::class.java) //코틀린 클래스를 자바로 자동 변형
    //정적 싱글톤 객체 생성
    companion object {
        private const val AUTHORIZATION = "Authorization" //안드로이 앱의 요청 데이터에서
        // 토큰이 포함된 헤더 값을 가져오기 위한 상수
        private const val BEARER = "Bearer" //헤더에서 토큰의 인증방법을 나타내는 상수
        private const val GRANT_TYPE = "grant_type" //토큰을 발행을 요청할 때 사용하는 파라미터 상수
        const val GRANT_TYPE_REFRESH = "grant_type_refresh" //토큰을 발행을 요청할 때 사용하는 파라미터 상수
        private val DEFAULT_ALLOWED_API_URLS = listOf( //토큰없이 사용할 수 있는 접근허용 URL 등록
            "POST" to "/api/signin",
            "POST" to "/api/users"
        ) //스프링 시큐리티 대신 사용 "GET" to "/api/products"
    }

    //요청처리 전에 호출(실행)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authHeader = request.getHeader(AUTHORIZATION) //안드로이드 앱 요청에 포함된 헤더의 Authorization 값을 반환 한다.
        if(authHeader.isNullOrBlank()) {
            val pair = request.method to request.servletPath
            if(!DEFAULT_ALLOWED_API_URLS.contains(pair)) {
                response.sendError(401)
                return false
            }
            return true
        } else {
            val grantType = request.getParameter("grant_type")
            val token = authHeader.replace(BEARER, "").trim()
            return handleToken(grantType, token, response)
        }
        //return super.preHandle(request, response, handler)
    }

    private fun handleToken(grantType: String?, token: String, response: HttpServletResponse) =
        //TODO("JWTUtil 클래스의 함수를 이용해서 토큰을 검증하고 AuthHolderService에 인증값을 저장.")
        try {
            val jwt = when(grantType) {
                GRANT_TYPE_REFRESH -> JWTUtil.verifyRefresh(token)
                else -> JWTUtil.verify(token)
            }
            val email = JWTUtil.extractEmail(jwt)
            authHolderService.set(email)
            true
        } catch (e: Exception) {
            logger.error("토큰 검증 실패, token = $token", e)
            response.sendError(401) //Unauthorized 인증오류
            false
        }

    //요청 처리 후 뷰가 렌더링 되기 전에 호출(실행)
    override fun postHandle( //AuthHolderService 에서 저장한 사용자 정보 스레드를 초기화 한다.
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        //super.postHandle(request, response, handler, modelAndView)
        authHolderService.clear()
    }
}