package org.kimilguk.shop.controller

import org.kimilguk.shop.common.ApiResponse
import org.kimilguk.shop.domain.product.Product
import org.kimilguk.shop.dto.product.ProductRequestDto
import org.kimilguk.shop.service.product.ProductCreateService
import org.kimilguk.shop.service.product.ProductService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

/**
 * 외부에서 접근해는 통로인 RestAPI @컨트롤러 ProductApiController 클래스
 */
@RequestMapping("/api")
@RestController
class ProductApiController( //생성자함수는 클래스를 초기화하는 기능
    private val productCreateService: ProductCreateService, //val 타입으로 new 키워드 대신 객체 생성(DI 주입)
    private val productService: ProductService,
) {
    private val logger = LoggerFactory.getLogger(this::class.java) //코틀린 클래스를 자바로 자동 변형
    @GetMapping("/products")
    fun search(
        @RequestParam productId: Long,
        @RequestParam(required = false) categoryId: Int?,
        @RequestParam direction: String,
        @RequestParam(required = false) limit: Int?
    ): ApiResponse {
        logger.info("상품목록1, $categoryId, $productId, $direction")
        return productService
            .search(categoryId, productId, direction, limit?:10)
            //.mapNotNull { return@mapNotNull Product::toProductListItemResponse }
            //toProductListItemResponse 결과가 null 이 아닐경우 map 데이터 등록, 사용하지 않아야 정상작동 함.
            //위 함수를 사용하지 않는다. 다음과 같은 오류남 kotlin Could not write JSON: Infinite recursion
            .let { ApiResponse.ok(it) }
    }

    @PostMapping("/products")
    fun create(
        @RequestBody request: ProductRequestDto
    ) = ApiResponse.ok(
            productCreateService.create(request)
        )
}