package com.planx.petstest.presentation

import androidx.recyclerview.widget.DiffUtil
import com.planx.petstest.domain.Pet

class PetsDiffUtil: DiffUtil.ItemCallback<Pet>() {
    override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean =
        oldItem.name == newItem.name


    override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean =
        oldItem == newItem
}