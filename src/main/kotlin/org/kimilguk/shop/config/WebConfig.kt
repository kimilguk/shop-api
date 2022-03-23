package org.kimilguk.shop.config

import org.kimilguk.shop.common.TokenValidationInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * 스프링에서 인터셉터 클래스를 사용하기 위해 등록한다.
 */
@Configuration //자동으로 실행가능한 스프링 클래스인 빈으로 등록한다.(WebMvcConfigurer 상속하면 콜백 자동실행된다.)
class WebConfig @Autowired constructor(
    private val tokenValidationInterceptor: TokenValidationInterceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry) //생략해도 된다.
        registry.addInterceptor(tokenValidationInterceptor) //.으로 이어지는 함수를 체인함수라고 한다.
            .addPathPatterns("/api/**") //지정된 패턴에서 인터셉터 클래스가 콜백 자동실행 된다.
    }
}