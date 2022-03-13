package org.kimilguk.shop.common

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

/**
 * 개발자가 예외처리를 중간에 가로채서 처리하는 클래스이다.
 */
@ControllerAdvice //중간에 가로채는 인터셉터 기능으로 명시
@RestController //요청에 대한 응답을 body 내용만 반환한다.
class ShopExceptionHandler {
    private val loader = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(ShopException::class) //개발자가 지정한 오류인 ShopException 의 String 형 메세지만 받는다.
    fun handleShopException(e: ShopException): ApiResponse {
        loader.error("API 에러", e)
        return ApiResponse.error(e.message)
    }

    @ExceptionHandler(Exception::class) //일반적인 오류인 Exception 의 String 형 메세지만 받는다.
    fun handleException(e: Exception): ApiResponse {
        loader.error("API 에러", e)
        return ApiResponse.error("알수 없는 오류가 발생했습니다.")
    }
}