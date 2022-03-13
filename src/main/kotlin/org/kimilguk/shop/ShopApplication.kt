package org.kimilguk.shop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//@Configuration, @EnableAutoConfiguration, @ComponentScan 3가지를 묶어놓은 애노테이션(아래)
@SpringBootApplication //스프링부트애플리케이션을 명시하고, @RestController, @Service 등을 스프링빈으로 자동 등록한다.
class ShopApplication {

}
fun main() { //주의 자바와 틀리게 메인 함수는 클래스 외부에 존재 해야 한다.
    runApplication<ShopApplication>() //내장된 run~함수를 사용해 현재 클래스를 실행 시킨다.
}