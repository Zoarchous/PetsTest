package com.planx.petstest.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.planx.petstest.R

class PetsViewHolder (val view: View): RecyclerView.ViewHolder(view) {
    val petImage: ImageView = view.findViewById(R.id.petImage)
    val petName: TextView = view.findViewById(R.id.petName)
    val petSpecies: TextView = view.findViewById(R.id.petSpecies)
}