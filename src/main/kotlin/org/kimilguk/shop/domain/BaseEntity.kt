package org.kimilguk.shop.domain

import java.util.*
import javax.persistence.*

/**
 * 모든 엔티티에서 공통으로 사용할 등록일과 수정일 엔티티 클래스
 */
@MappedSuperclass // 공통 엔티티 클래스임을 명시하면 다른 엔티티 클래스에서 상속해서 사용가능하다.
open class BaseEntity { //open -> abstract(final 기능-여기서만 사용=상속하지 않는다.)
    @Id //PK로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //입력값이 null 이라도 자동증가 값 지정
    open var id: Long? = null //open 지정이 필수이다.
    open var createdAt: Date? = null
    open var updatedAt: Date? = null

    @PrePersist //데이터베이스에 새 데이터가 저장되기 전에 자동으로 실행
    fun prePersist() {
        createdAt = Date()
        updatedAt = Date()
    }
    @PreUpdate //데이터베이스에 새 데이터가 수정되기 전에 자동으로 실행
    fun preUpdate() {
        updatedAt = Date()
    }
}
