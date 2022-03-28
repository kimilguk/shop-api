package org.kimilguk.shop.dto.product

/**
 * 서비스에서 상품 등록요청 시 전송내용이 임시 저장될 데이터 ProductRequestDto 클래스
 */
data class ProductRequestDto(
    val name: String, //상품명
    val description: String, //상품설명
    val price: Int, //상품가격
    val categoryId: Int //상품분류
)
