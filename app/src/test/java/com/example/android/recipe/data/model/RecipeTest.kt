package com.example.android.recipe.data.model


import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import timber.log.Timber
@Config(manifest=Config.NONE)
@RunWith(AndroidJUnit4::class)
class RecipeTest {
    //helper fields
    //mock help class

    //region for test methods
    @Test
    fun water(){
       val stream = this.javaClass.classLoader?.getResourceAsStream("recipes/water.txt")?.bufferedReader()?.readLines()
        assertThat(stream, `is`(notNullValue()))
        Timber.i("data", stream)

        val recipe = readFromStreams(stream)
        assertThat(recipe, `is`(notNullValue()))

        //parse
        assertThat("water",`is`(recipe.id))
        assertThat("Water",`is`(recipe.title))
        assertThat("Put glass under tap. Open tap. Close tap. Drink.",`is`(recipe.description))
    }

    @Test
    fun mix(){
        val stream = this.javaClass.classLoader?.getResourceAsStream("recipes/mixed.txt")?.bufferedReader()?.readLines()
        assertThat(stream, `is`(notNullValue()))
        Timber.i("data", stream)

        val recipe = readFromStreams(stream)
        assertThat(recipe, `is`(notNullValue()))

        //parse
        assertThat("punch",`is`(recipe.id))
        assertThat("Punch",`is`(recipe.title))
        assertThat("Juice of 3 lemons\n" +
                "1 orange\n" +
                "1 pint grape juice\n" +
                "1 cup sugar\n" +
                "1 cup water\n" +
                "1 pine apple juice\n" +
                "Mix all together and strain. Add large piece of ice.",`is`(recipe.description))
    }

    //region for private methods  
}