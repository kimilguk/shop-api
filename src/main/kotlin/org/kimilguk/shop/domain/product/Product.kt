package org.kimilguk.shop.domain.product

import org.kimilguk.shop.domain.BaseEntity
import javax.persistence.*

/**
 * 상품등록에 필요한 JPA용 @Entity 클래스, JPA 구현 하이버네이트가 자동으로 테이블을 생성시켜줌
 */
@Entity //(name = "product") //테이블명 생략가능
class Product( //생성자 매개변수를 지정해서 null 을 허용하지 않는 필수 값으로(기본)으로 지정
    @Column(length = 40)
    var name: String, //상품명
    @Column(length = 500)
    var description: String, //상품설명
    var price: Int, //상품가격
    var categoryId: Int, //상품분류
    @Enumerated(EnumType.STRING) //열거형 데이터 타입
    var status: ProductStatus, //상품상태(판매중, 품절)
    var userId: Long //등록자 아이디
) : BaseEntity() {
}