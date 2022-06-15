package com.example.android.recipe.test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.android.recipe.injection.RecipeAppTest

class CustomTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, RecipeAppTest::class.qualifiedName , context)
    }
}