package org.kimilguk.shop.common

/**
 * 예외 처리핸들러에서 에러를 전파하는 RuntimeException 타입의 클래스 이다.
 */
class ShopException(message: String) : RuntimeException(message)
//해석하면, RuntimeException("개발자가 throw 로 던진 에러메시지")를 상속받아서 반환한다.