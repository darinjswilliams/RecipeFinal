package com.example.android.recipe.data.model.local

import android.content.Context
import android.content.SharedPreferences
import timber.log.Timber

class SharedPreferencesFavorites(var context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences("favorites.xml", Context.MODE_PRIVATE)

    fun get(id: String): Boolean {
        Timber.d("fun get: id : ${id}")
        return  pref.getBoolean(id, false)
    }

    fun put(id: String, favorite: Boolean) {
        val editor = pref.edit()

        Timber.d("fun put: favorite: $favorite")
        when (favorite) {
            true -> editor.putBoolean(id, true)
            else -> editor.remove(id)
        }

        //save changes
        editor.apply()
    }

    fun toggle(id: String): Boolean{
       val favorite = get(id)

        Timber.d("before calling put $favorite")
       put(id, !favorite)

        Timber.d("after calling toggle : $id, $favorite")
       return !favorite
    }
}