package org.kimilguk.shop.domain.users

import java.util.*
import javax.persistence.*

/**
 * 회원가입에 필요한 JPA용 @Entity 클래스, JPA 구현 하이버네이트가 자동으로 테이블을 생성시켜줌
 */
@Entity
class Users( //생성자 매개변수를 지정해서 null 을 허용하지 않는 필수 값으로(기본)으로 지정
    var email: String,
    var password: String,
    var name: String
) {
    @Id //이 필드가 PK(Primary Key) 고유필드라고 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)//입력값이 null 이라도 자동증가 된다.
    var id: Long? = null

    var createdAt: Date? = null
    var updatedAt: Date? = null

    @PrePersist //데이터베이스에 새 데이터가 저장되기 전에 자동으로 실행
    fun prePersist() {
        createdAt = Date() //사용자 등록시 현재 날짜 자동 입력
        updatedAt = Date()
    }
    @PreUpdate //데이터베이스에 새 데이터가 수정되기 전에 자동으로 실행
    fun preUpdate() {
        updatedAt = Date() //사용자 수정시 현재 날짜 자동 입력
    }
}