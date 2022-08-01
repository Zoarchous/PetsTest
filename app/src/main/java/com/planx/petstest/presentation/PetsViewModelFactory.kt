package com.planx.petstest.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.planx.petstest.data.RepositoryImpl
import javax.inject.Inject

class PetsViewModelFactory @Inject constructor(
    private val repositoryImpl: RepositoryImpl
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetsViewModel::class.java)) {
            return PetsViewModel(
                repositoryImpl
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}