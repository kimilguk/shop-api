package org.kimilguk.shop.common

import org.slf4j.LoggerFactory

/**
 * 공통으로 사용할 응답데이터 형태를 성공여부, 메시지, 전송데이터로 구성된 데이터클래스 이다.
 */
data class ApiResponse(
    val success: Boolean,
    val data: Any? = null,
    val message: String? = null
) {
    companion object {//static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
        private val logger = LoggerFactory.getLogger(this::class.java) //코틀린 클래스를 자바로 자동 변형
        //fun ok(data: Any? = null) = ApiResponse(true, data) //ok 일때 data 를 전송받고
        fun ok(data: Any? = null): ApiResponse {
            logger.info("상품정보: ${data.toString()}")
            return ApiResponse(true, data) //ok 일때 data 를 전송받고
        }
        fun error(message: String? = null) = ApiResponse(false, message = message)//false 앨때 에러메세지를 전송받는다.
    }
}
