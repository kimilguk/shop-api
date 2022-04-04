package org.kimilguk.shop.dto.product

import org.kimilguk.shop.domain.product.Product

/**
 * 서비스에서 상품 목록출력 시 전송내용이 임시 저장될 데이터 ProductListRequestDto 클래스
 */
data class ProductListRequestDto(
    val id: Long, //PK(Primary Key) 고유키
    val name: String,
    val description: String,
    val price: Int,
    val status: String, //상품상태(판매중, 품절)
    val sellerId: Long //등록자 ID
)
//Product 엔티티의 개별상품를 반환하는 Product 확장함수(아래) 사용할 필요 없음.
/*fun Product.toProductListItemResponse(): ProductListRequestDto? {
    return id?.let {
        ProductListRequestDto(
            it,
            name,
            description,
            price,
            status.name,
            userId
        )
    }
}*/
//위 함수를 ProductApiController 에서 사용하지 않는다.
// 다음과 같은 오류남 kotlin Could not write JSON: Infinite recursion
