package com.planx.petstest.data

import android.app.Activity
import android.util.Log
import com.planx.petstest.domain.Pet
import com.planx.petstest.domain.PetsList
import com.planx.petstest.domain.Repository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(): Repository {

    override fun getPets(activity: Activity): MutableList<Pet> {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<PetsList> = moshi.adapter(PetsList::class.java)
        val pets = jsonAdapter.fromJson(loadJsonFromAsset(activity))
        val petsList = mutableListOf<Pet>()
        pets?.pets?.let { petsList.addAll(it) }
        return petsList
    }
    private fun loadJsonFromAsset(activity: Activity): String {
        var string = ""
        try {
            val inStr = activity.assets.open("pets-list")
            val s = inStr.available()
            val byteArray = ByteArray(s)
            inStr.read(byteArray)
            inStr.close()
            string = String(byteArray)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("!!!", string)
        return string
    }
}