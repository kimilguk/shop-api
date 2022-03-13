class ParamFunc {//고차함수 Higher Order Function 에서 아래 () -> 반환타입(값 변수 생략) 가 함수타입을 말한다.
    fun higherOrderFunction(stringParam: String, funcParam: () -> String) {
        println(stringParam + funcParam())
    }
}
//메인 실행함수(아래)
fun main() {
    val paramFunc = ParamFunc() //new 키워드 없이 객체 생성
    paramFunc.higherOrderFunction("안녕 ") {
        "코틀린"
    }
}
