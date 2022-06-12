package com.example.android.recipe.ui.recipe

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test

internal class RecipeActivityTest {
    //helper fields
    //mock helper class
    lateinit var SUT: RecipeActivity

    @get:Rule
    val activityRule = activityScenarioRule<RecipeActivity>()

    fun setUp() {
        SUT = RecipeActivity()

    }

    //region for test methods
    @Test
    fun recipeNotFound(){
        activityRule.launchActivity(null)

        onView(withId(R.id.description))
            .check(matches(withText(R.string.recipe_not_found)))
    }

    //region for private methods  
}