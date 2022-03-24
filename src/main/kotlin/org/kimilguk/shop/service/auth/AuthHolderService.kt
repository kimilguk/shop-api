package org.kimilguk.shop.service.auth

import org.kimilguk.shop.domain.users.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 서버세션의 사용자정보 대신에 클라이언트에서 보낸 토큰정보로 서버의 ThreadLocal 클래스에서 로그인 정보를 유지한다.
 */
@Service //스프링빈으로 사용하도록 명시
class AuthHolderService @Autowired constructor(
    private val usersRepository: UsersRepository
) {
    //토큰으로 접속한 사용자의 스레드 로컬 데이터를 AuthHolder 클래스에 저장한다.
    private val authHolder = ThreadLocal.withInitial { AuthHolder() }
    val id: Long? get() = authHolder.get().id
    val email: String? get() = authHolder.get().email
    val name: String? get() = authHolder.get().name

    fun set(email: String) = usersRepository
        .findByEmail(email)?.let { users ->
            this.authHolder.get().apply {
                this.id = users.id
                this.email = users.email
                this.name = users.name
            }.run { authHolder::set }//더블콜론은 클래스를 참조해서 set 함수를 실행한다.
        }
    fun clear() {
        authHolder.remove()//클라이언트 앱의 요청이 끝나면 스레드 로컬 데이터=로그인정보 를 지운다.
    }
    class AuthHolder {
        var id: Long? = null
        var email: String? = null
        var name: String? = null
    }
}