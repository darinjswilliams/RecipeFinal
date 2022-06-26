package com.example.android.recipe.test

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not
import timber.log.Timber

abstract class ScreenRobot<T>() {
    fun checkIsHidden(@IdRes vararg viewIds: Int): ScreenRobot<T> {
        for (viewId in viewIds) {
            Timber.i("*****viewid...$viewId")
            onView(withId(viewId))
                .noActivity()
                .check(matches(not(isDisplayed())))
        }
        return this
    }

    fun checkViewHasText(@IdRes viewId: Int, @StringRes stringId: Int): ScreenRobot<T>{
        onView(withId(viewId))
            .check(matches(withText(stringId)))

        return this
    }

    fun checkIsSelected(@IdRes vararg viewIds: Int): ScreenRobot<T> {
        for (viewId in viewIds) {
            onView(withId(viewId))
                .perform(click())
                .check(matches(isSelected()))

        }
        return this
    }
}