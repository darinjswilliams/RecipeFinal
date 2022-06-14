package com.example.android.recipe.ui.recipe

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.recipe.R
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeActivityTest {
    //helper fields
    //mock helper class

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
     * and close it after each test. This is a replacement for
     * [androidx.test.rule.ActivityTestRule].
     */
    @get:Rule
    val activityRule = ActivityScenarioRule(RecipeActivity::class.java)


    @After
    fun cleanUp() {
        activityRule.scenario.close()
    }

    //region for test methods
    @Test
    fun recipeNotFound() {

        launchActivity<RecipeActivity>(null)

        onView(withId(R.id.description))
            .check(matches(withText(R.string.recipe_not_found)))

        onView(withId(R.id.title))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun clickToFavorite() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), RecipeActivity::class.java)
            .putExtra(RecipeActivity.KEY_ID, "creamed_carrots")

        launchActivity<RecipeActivity>(intent)

        onView(withId(R.id.title))
            .check(matches(withText("Creamed Carrots")))
            .check(matches(not(isSelected())))
            .perform(click())
            .check(matches(isSelected()))

    }


    //region for private methods
}