package com.example.android.recipe.data.model.local

class InMemoryFavorites : Favorites {
    private val map = hashMapOf<String, Boolean>()

    override fun get(id: String): Boolean {
        return map[id] ?: false
    }

    override fun toggle(id: String): Boolean {
        val value = get(id)
        map[id] = !value
        return !value
    }

    fun put(id: String, value: Boolean) {
        map[id] = value
    }

    fun clear() {
        map.clear()
    }
}