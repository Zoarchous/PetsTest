package com.planx.petstest.domain

import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
data class PetsList(
    val pets: List<Pet>
)