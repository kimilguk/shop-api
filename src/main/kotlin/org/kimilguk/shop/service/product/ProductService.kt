package org.kimilguk.shop.service.product

import org.kimilguk.shop.domain.product.Product
import org.kimilguk.shop.domain.product.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

/**
 * 상품목록을 읽어오는 서비스로직 클래스
 * 검색조건: 카테고리 id, 상품의 id, 검색방향(상/하), 읽어올 개수
 */
@Service
class ProductService @Autowired constructor(//생성자함수는 클래스를 초기화하는 기능
    private val productRepository: ProductRepository //val 타입으로 new 키워드 대신 객체 생성(DI 주입)
){ //클래스 body
    private val logger = LoggerFactory.getLogger(this::class.java) //코틀린 클래스를 자바로 자동 변형
    fun search(
        categoryId: Int?,
        productId: Long,
        direction: String,
        limit: Int
    ): List<Product> {
        val pageable = PageRequest.of(0, limit)
        val condition = ProductSearchCondition(
            categoryId != null, //카테고리 Id가 없으면 널 전체검색 현재 h2 데이터베이스에서는 필수로 해야 된다.
            direction
        )
        logger.info("$direction 파라미터 $condition $pageable")
        return when(condition) {
            NEXT_IN_CATEGORY -> productRepository
                .findByCategoryIdAndIdLessThanOrderByIdDesc(categoryId, productId, pageable)
            PREV_IN_CATEGORY -> productRepository
                .findByCategoryIdAndIdGreaterThanOrderByIdDesc(categoryId, productId, pageable)
            else -> throw IllegalArgumentException("상품 검색 조건 오류")
        }
    }

    data class ProductSearchCondition(val categoryIdIsNotNull: Boolean, val direction: String)
    companion object {//static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
        val NEXT_IN_CATEGORY = ProductSearchCondition(true, "next")
        val PREV_IN_CATEGORY = ProductSearchCondition(true, "prev")
    }
}

