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
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.recipe.R
import com.example.android.recipe.data.model.local.InMemoryFavorites
import com.example.android.recipe.injection.RecipeAppTest
import com.example.android.recipe.test.RecipeRobot
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeActivityTest {
    //helper fields
    //mock helper class

    companion object{

        private const val CARROT_ID = "creamed_carrots"
    }

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
     * and close it after each test. This is a replacement for
     * [androidx.test.rule.ActivityTestRule].
     */
    @get:Rule
    val activityRule = ActivityScenarioRule(RecipeActivity::class.java)

    private lateinit var favorites: InMemoryFavorites

    @Before
    fun clearFavorites(){
       val app  =  InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        favorites  = (app as? RecipeAppTest)?.getFavorites() as InMemoryFavorites

        favorites.clear()
    }

    @After
    fun cleanUp() {
        activityRule.scenario.close()
    }

    //region for test methods
    @Test
    fun recipeNotFound() {

        RecipeRobot()
            .launch(activityRule)
            .noTitle()
            .description(R.string.recipe_not_found)
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

    @Test fun alreadyFavorite(){

        favorites.put(CARROT_ID, true)

        launchRecipe(CARROT_ID)
        onView(withId(R.id.title))
            .check(matches(isSelected()))

    }

    private fun launchRecipe(id: String) {
        val intent = Intent(ApplicationProvider.getApplicationContext(), RecipeActivity::class.java)
            .putExtra(RecipeActivity.KEY_ID, id)

        launchActivity<RecipeActivity>(intent)
    }


    //region for private methods
}