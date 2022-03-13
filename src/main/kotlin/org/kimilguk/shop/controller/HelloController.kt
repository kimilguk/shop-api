package org.kimilguk.shop.controller

import org.kimilguk.shop.common.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController //요청에 대한 응답을 body 내용만 반환한다.
class HelloController {
    @GetMapping("/api/hello")
    fun hello() = ApiResponse.ok("Hello 코틀린")
}