package com.planx.petstest.domain

import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
data class Pet(
    val name: String,
    val species: String,
    val birthYear: Int,
    val photo: String,
    val description: String,
    val favFoods: List<String>?,
)
