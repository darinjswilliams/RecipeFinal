package com.example.android.recipe.test

import android.content.Intent
import androidx.annotation.StringRes
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.LayoutAssertions.noOverlaps
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.recipe.R
import com.example.android.recipe.data.model.local.InMemoryFavorites
import com.example.android.recipe.injection.RecipeAppTest
import com.example.android.recipe.ui.recipe.RecipeActivity
import com.example.android.recipe.ui.recipe.RecipeActivityTest
import junit.framework.AssertionFailedError
import org.junit.Before
import androidx.test.ext.junit.rules.ActivityScenarioRule as RulesActivityScenarioRule

class RecipeRobot() : ScreenRobot<RecipeRobot>() {

    private var favorites: InMemoryFavorites

    init {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        favorites = (app as? RecipeAppTest)?.getFavorites() as InMemoryFavorites

        favorites.clear()
    }

    fun launch(rule: RulesActivityScenarioRule<RecipeActivity>): RecipeRobot {
       rule.scenario.onActivity { Intent(it, RecipeActivity::class.java) }
        return this
    }

    fun launch(rule: RulesActivityScenarioRule<RecipeActivity>, id: String): RecipeRobot {

        rule.apply {
            launchActivity<RecipeActivity>().use { scenario ->
                scenario.onActivity {
                    it.startActivity(Intent(it, RecipeActivity::class.java)
                        .putExtra(RecipeActivity.KEY_ID, id)
                    )
                }
            }
        }
        return this
    }

    fun noTitle(): RecipeRobot {
        return checkIsHidden(R.id.title) as RecipeRobot
    }

    fun description(@StringRes stringId: Int): RecipeRobot {
        return checkViewHasText(R.id.description, stringId) as RecipeRobot
    }

    fun setFavorites(id: String): RecipeRobot {
        favorites.put(id, true)
        return this
    }

    fun isFavorite(): RecipeRobot {
        return checkIsSelected(R.id.title) as RecipeRobot
    }
}