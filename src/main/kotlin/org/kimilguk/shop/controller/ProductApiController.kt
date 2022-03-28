package org.kimilguk.shop.controller

import org.kimilguk.shop.common.ApiResponse
import org.kimilguk.shop.dto.product.ProductRequestDto
import org.kimilguk.shop.service.product.ProductCreateService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 외부에서 접근해는 통로인 RestAPI @컨트롤러 ProductApiController 클래스
 */
@RequestMapping("/api")
@RestController
class ProductApiController( //생성자함수는 클래스를 초기화하는 기능
    private val productCreateService: ProductCreateService //val 타입으로 new 키워드 대신 객체 생성(DI 주입)
) {
    @PostMapping("/products")
    fun create(
        @RequestBody request: ProductRequestDto
    ) = ApiResponse.ok(
            productCreateService.create(request)
        )
}