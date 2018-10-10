package com.danielebottillo.blog

import org.junit.Test

class ExampleUnitTest {

    /*@Test
    fun `food is pizza and have 4 slices`() {
        val underTest = generateFood()

        assertThat(underTest is Food.Pizza).isTrue()
        assertThat((underTest as Food.Pizza).slices).isEqualTo(4)
    }*/

    /*@Test
    fun `food is pizza and have 4 slices`() {
        val underTest = generateFood()

        assertWithMessage("food should be pizza with 4 slices")
                .about(food())
                .that(underTest)
                .isPizza(slices = 4)
    }*/

    @Test
    fun `food is pizza and have 4 slices`() {
        val underTest = generateFood()

        assertThat(underTest).isPizza(slices = 4)
    }

    private fun generateFood(): Food {
        return Food.Pizza(slices = 4)
    }
}