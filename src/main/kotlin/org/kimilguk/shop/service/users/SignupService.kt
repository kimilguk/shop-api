package org.kimilguk.shop.service.users

import org.kimilguk.shop.common.ShopException
import org.kimilguk.shop.domain.users.Users
import org.kimilguk.shop.domain.users.UsersRepository
import org.kimilguk.shop.web.users.SignupDto
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 회원가입 서비스 로직 함수를 제공하는 클래스
 */
@Service
class SignupService @Autowired constructor(//생성자 매개변수를 지정해서 외부 클래스를 주입받는다.
    private val usersRepository: UsersRepository //CRUD 담당 외부 레포지토리 객체생성(주입)
) {
    fun signup(request: SignupDto) {
        validateRequest(request)//전송데이터 이메일, 이름, 암호 유효성검사
        checkDuplicate(request.email)//이메일 중복 입력방지
        registerUsers(request)//실제 등록 레포지토리 함수 실행
    }

    private fun registerUsers(request: SignupDto) =
        with(request) { //request 블록지정
            //TODO("회원을 등록하는 함수를 실행한다. BCrypt 암호화 저장 필요")
            val cryptPassword = BCrypt.hashpw(request.password, BCrypt.gensalt())//암호화시 소금치기
            val users = Users(request.email, cryptPassword, request.name)
            usersRepository.save(users)
        }

    /*private fun checkDuplicate(email: String): Users? {
        //TODO("중복 아이디인 email 이 존재하는지 체크")
        return usersRepository.findByEmail(email)?.let {
            throw ShopException("이미 사용중인 이메일 입니다.")
        }
    } 위 코드를 return 을 단축하면 아래와 같다. 앞으로는 아래와 같은 리턴 방식을 사용한다. */
    private fun checkDuplicate(email: String) =
        //TODO("중복 아이디인 email 이 존재하는지 체크")
        usersRepository.findByEmail(email)?.let { //쿼리 결과가 null 인지 체크해서 값이 있다면 {구현}
            //값이 null 인 반대경우는 ?: 엘비스연산자 사용
            throw ShopException("이미 사용중인 이메일 입니다.")
        }

    private fun validateRequest(request: SignupDto) {
        //TODO("전송데이터 이메일, 이름, 암호 유효성검사")
        validateEmail(request.email)
        validateName(request.name)
        validatePassword(request.password)
    }

    private fun validatePassword(password: String) {
        //TODO("암호 입력값 체크")
        if(password.trim().length !in 8..20) //in 단일이아닌 다중 조건일 때 사용하는 키워드 이다.
            throw ShopException("비밀번호는 8자 이상 20자 이상이어야 한다.")
    }

    private fun validateName(name: String) {
        if(name.trim().length !in 2..20) //이름 길이가 2~20 사이가 아니라면 중지
            throw ShopException("이름은 2자 이상 20자 이하여야 합니다.")
    }

    private fun validateEmail(email: String) {
        //TODO("정규표현식 사용 이메일 여부 체크")
        val isNotValidEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
            .toRegex(RegexOption.IGNORE_CASE)
            .matches(email)
            .not() //위 email 매칭 결과가 false 라면
        if(isNotValidEmail) {
            throw ShopException("이메일 형식이 올바르지 않습니다.")
        }
    }
}