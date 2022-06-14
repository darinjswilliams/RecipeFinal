package com.example.android.recipe.data.model.local

class InMemoryFavorites : Favorites {
    private val map = HashMap<String, Boolean>()

    override fun get(id: String): Boolean {
        return map[id] ?: false
    }

    override fun toggle(id: String): Boolean {
        val value = get(id)
        map.put(id, !value)
        return !value
    }

    fun put(id: String, value: Boolean) {
        map.put(id, value)
    }
}