package org.kimilguk.shop.domain.product

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 엔티티 클래스에 데이터를 CRUD 하기 위한 기능 함수를 제공하는 인터페이스
 */
//@Repository //이 애노테이션은 생략해도 정상작동 된다.
interface ProductRepository : JpaRepository<Product, Long>{
    //Product 클래스에 CRUD 함수를 제공한다.
    // 기본 PK 검색을 제외한 필드의 검색은 개발자가 추가한다. 페이징 처리는 Pageable 클래스를 상속한다.(아래)
    fun findByCategoryIdAndIdGreaterThanOrderByIdDesc( //상품리스트가 위쪽으로 스크롤 될때
        categoryId: Int?, id: Long, pageable: Pageable
    ): List<Product> //Pageable 은 화면당 보여줄 개수와 몇번째 페이지를 가져올 지 결정하는 객체이다.

    //상품 리스트가 아래쪽으로 스크롤될 때 호출되는 함수로, 현재 화면에 로딩된 것보다 이전(카테고리가 같고, id 크기가 적은) 데이터를 추출하는 쿼리가 실행 된다.(아래)
    fun findByCategoryIdAndIdLessThanOrderByIdDesc(
        categoryId: Int?, id: Long, pageable: Pageable
    ): List<Product>

    fun findByIdGreaterThanAndNameLikeOrderByIdDesc(
        id: Long, keyword: String, pageable: Pageable
    ): List<Product>

    fun findByIdLessThanAndNameLikeOrderByIdDesc(
        id: Long, keyword: String, pageable: Pageable
    ): List<Product>
}