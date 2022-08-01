package com.planx.petstest.presentation

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.planx.petstest.domain.GetPetsUseCase
import com.planx.petstest.domain.Pet
import com.planx.petstest.domain.Repository
import kotlinx.coroutines.launch

class PetsViewModel(
    repository: Repository,
) : ViewModel() {
    private val getPetsUseCase = GetPetsUseCase(repository)
    val petsList = MutableLiveData<MutableList<Pet>>()

    fun getPets(activity: Activity) = viewModelScope.launch {
        petsList.postValue(getPetsUseCase.getPets(activity))
    }
}