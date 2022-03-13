import io.kotlintest.shouldBe
import io.kotlintest.specs.AnnotationSpec

class AnnotationSpecExample : AnnotationSpec() {
    @BeforeEach
    fun beforeTest() {
        println("테스트 실행 전")
    }
    @Test
    fun test() {
        fun higherOrderFunction(stringParam: String, funcParam: () -> String) {
            println(stringParam + funcParam())
        }
        higherOrderFunction("안녕 ") {
            "코틀린"
        }
    }
}