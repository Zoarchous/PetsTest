package com.planx.petstest.domain

import android.app.Activity

interface Repository {
    fun getPets(activity: Activity): MutableList<Pet>
}