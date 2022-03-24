package org.kimilguk.shop.domain.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 엔티티 클래스에 데이터를 CRUD 하기 위한 기능 함수를 제공하는 인터페이스
 */
@Repository //이 애노테이션은 생략해도 정상작동 된다. 하지만, 명시적으로 사용한다.
interface UsersRepository : JpaRepository<Users, Long> {
    //Users 클래스에 CRUD 함수를 제공한다.
    // PK 를 검색을 제외한 필드의 검색은 개발자가 추가한다.(아래)
    fun findByEmail(email: String): Users? //쿼리 함수 반환 타입은 Users 클래스 데이터 객체 이다.
}