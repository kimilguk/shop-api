package org.kimilguk.shop.controller

import org.kimilguk.shop.common.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController //요청에 대한 응답을 body 내용만 반환한다.
class HelloController {
    @GetMapping("/api/hello")
    fun hello(): ApiResponse {
        //TODO("Json 반환값 확인")//구현 되지 않았을 경우 여기서 강제로 에러 발생
        return ApiResponse.ok("Hello 코틀린")
    }
    //fun hello() = ApiResponse.ok("Hello 코틀린")
}