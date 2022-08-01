package com.planx.petstest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.planx.petstest.R
import com.planx.petstest.domain.Pet
import jp.wasabeef.glide.transformations.CropSquareTransformation

class PetsAdapter :
    ListAdapter<Pet, PetsViewHolder>(PetsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.pets_item,
            parent,
            false
        )
        return PetsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        val item = getItem(position)
        Glide.with(holder.petImage)
            .load(item.photo)
            .apply(RequestOptions.bitmapTransform(CropSquareTransformation()))
            .into(holder.petImage)
        holder.petName.text = item.name
        holder.petSpecies.text = item.species
    }

}