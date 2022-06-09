package com.example.android.recipe.data.model.local

import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.core.Is.`is`
import org.junit.Test


class RecipeStoreTest {
    //helper fields
    //mock helper class
    private lateinit var SUT: RecipeStore

    @Test
    fun setUp() {
        SUT = RecipeStore(context = InstrumentationRegistry.getInstrumentation().targetContext,
      null.toString()
        )
        assertThat(SUT, `is`(notNullValue()))

    }

   @Test
    fun nullDirectory() {
        //Arrange - Expected and When arrangement
       setUp()
        //Act - Actual, calls implementation code
        //Assert - Verify and Assertion
       assertThat(SUT.recipes, `is`(notNullValue()))
        assertThat(SUT.recipes.size, `is`(0))
    }//subjectUnderTest_actionOrInpud_resultState

    @Test
    fun countDirectory() {
        //Arrange - Expected and When arrangement
        SUT = RecipeStore(context = InstrumentationRegistry.getInstrumentation().targetContext,
            "recipes"
        )
        //Act - Actual, calls implementation code
        //Assert - Verify and Assertion
        assertThat(SUT.recipes, `is`(notNullValue()))
        assertThat(SUT.recipes.size, `is`(4))
    }//

    @Test
    fun getChocolatePudding() {
        //Arrange - Expected and When arrangement
        SUT = RecipeStore(context = InstrumentationRegistry.getInstrumentation().targetContext,
            "recipes"
        )
        //Act - Actual, calls implementation code
        val recipe = SUT.getRecipe("chocolate_pudding")
        //Assert - Verify and Assertion
        assertThat(recipe, `is`(notNullValue()))
        assertThat(recipe?.id, `is`("chocolate_pudding"))
        assertThat(recipe?.title, `is`("Chocolate Pudding"))

    }//


    //region for test methods

    //region for private methods  
}