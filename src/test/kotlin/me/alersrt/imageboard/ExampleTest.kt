package me.alersrt.imageboard

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Ignore

@SpringBootTest
class ExampleTest {

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