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
    private val loader = LoggerFactory.getLogger(this::class.java)//코틀린에서 더블콜론(::)은 리플렉션(참조)을 위해 사용합니다.  끝에 .java 를 사용한 이유는 JVM에서 실행시 Kclass 에서 자바클래스로 바꾸어 주기 위해서 이다.

    @ExceptionHandler(ShopException::class) //개발자가 지정한 오류인 ShopException 클래스를 참조한다.
    fun handleShopException(e: ShopException): ApiResponse {
        loader.error("API 에러", e)
        return ApiResponse.error(e.message)
    }

    @ExceptionHandler(Exception::class) //일반적인 오류인 Exception 클래스를 참조한다.
    fun handleException(e: Exception): ApiResponse {
        loader.error("API 에러", e)
        return ApiResponse.error("알수 없는 오류가 발생했습니다.")
    }
}