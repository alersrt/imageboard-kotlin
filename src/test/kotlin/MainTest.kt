import kotlin.test.Ignore
import kotlin.test.Test

class MainTest {

    @Test
    fun success() {
        assert(true)
    }

    @Ignore
    @Test
    fun fail() {
        assert(false)
    }
}