package com.example.android.recipe.data.model


import android.os.Parcelable
import com.example.android.recipe.data.model.Prefix.ID_PREFIX
import com.example.android.recipe.data.model.Prefix.NEW_LINE
import com.example.android.recipe.data.model.Prefix.TITLE_PREFIX
import kotlinx.parcelize.Parcelize

import timber.log.Timber

@Parcelize
data class Recipe(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null
): Parcelable

fun readFromStreams(data: List<String>?): Recipe {

    lateinit var id: String
    lateinit var title: String
    var descBuilder = StringBuilder()

    try {
        if (data != null) {
            for (line in data){
                if(line.startsWith(ID_PREFIX)){
                    id = line.substring(ID_PREFIX.length)
                    continue
                }

                if(line.startsWith(TITLE_PREFIX)){
                    title = line.substring(TITLE_PREFIX.length)
                    continue
                }

                if(descBuilder.isNotEmpty()){
                    descBuilder.append(NEW_LINE)
                }
                descBuilder.append(line)
            }
        }
    } catch (e: Exception) {
        Timber.e(e.localizedMessage)
    }

    return Recipe(id, title, descBuilder.toString())
}


object Prefix {
    const val ID_PREFIX = "id="
    const val TITLE_PREFIX = "title="
    const val NEW_LINE = "\n"
    }