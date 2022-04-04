package org.kimilguk.shop.service.product

import org.kimilguk.shop.common.ShopException
import org.kimilguk.shop.domain.product.Product
import org.kimilguk.shop.domain.product.ProductRepository
import org.kimilguk.shop.domain.product.ProductStatus
import org.kimilguk.shop.dto.product.ProductRequestDto
import org.kimilguk.shop.service.auth.AuthHolderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.reflect.KFunction1

/**
 * 상품등록 서비스 로직 함수를 제공하는 서비스로직 클래스
 */
@Service
class ProductCreateService @Autowired constructor(//생성자함수는 클래스를 초기화하는 기능
    private val productRepository: ProductRepository, //val 타입으로 new 키워드 대신 객체 생성(DI 주입)
    private val authHolderService: AuthHolderService //로그인정보를 ThreadLocal 객체로 저장
) {
    fun create(request: ProductRequestDto) =
        authHolderService.id?.let { userId ->
            request.validateRequest()
            request.toProduct(userId)
                .run(::save) //:: 더블콜론은 참조하겠다는 연산자 이다.
        }?: throw ShopException("상품등록에 필요한 사용자 정보가 없습니다.")//엘비스 연산자 사용
    private fun save(product: Product): Product {
        return productRepository.save(product)
    }
}
//아래와 같은 방식으로 ProductRequestDto 외부 클래스에 현재 클래스에서만 사용하는 함수를 추가 할 수도 있다.
private fun ProductRequestDto.toProduct(userId: Long): Product {
    //TODO("Request로 받은 데이터를 @엔티티 객체에 바인딩")
    return Product(
        name,
        description,
        price,
        categoryId,
        ProductStatus.SELLABLE,
        userId
    )
}
//아래와 같은 방식으로 ProductRequestDto 외부 클래스에 현재 클래스에서만 사용하는 함수를 추가 할 수도 있다.
private fun ProductRequestDto.validateRequest() {
    when { //조건문 when{ else }
        //TODO("전송받은 값 유효성 검사")
        name.length !in 1..40 ||
                description.length !in 1..500 ||
                price <= 0 ->
            throw ShopException("올바르지 않은 상품 정보입니다.")
        else -> {
        }
    }
}