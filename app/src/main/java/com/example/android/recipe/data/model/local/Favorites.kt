package com.example.android.recipe.data.model.local

interface Favorites {
    fun get(id: String): Boolean
    fun toggle(id: String): Boolean

}