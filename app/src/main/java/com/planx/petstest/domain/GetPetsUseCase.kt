package com.planx.petstest.domain

import android.app.Activity
import javax.inject.Inject

class GetPetsUseCase @Inject constructor(private val repository: Repository) {
    fun getPets(activity: Activity) = repository.getPets(activity)
}