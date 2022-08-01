package com.planx.petstest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.planx.petstest.R
import com.planx.petstest.databinding.FragmentPetsBinding
import com.planx.petstest.domain.Pet
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.CropSquareTransformation
import javax.inject.Inject

@AndroidEntryPoint
class PetsFragment : Fragment() {
    private lateinit var binding: FragmentPetsBinding
    private lateinit var petsViewModel: PetsViewModel

    @Inject
    lateinit var petsViewModelFactory: PetsViewModelFactory

    private lateinit var petsAdapter: PetsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPetsBinding.inflate(layoutInflater)
        setupViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        petsViewModel.petsList.observe(viewLifecycleOwner) {
            petsAdapter.submitList(it)
        }
    }

    private fun setupViewModel() {
        petsViewModel =
            ViewModelProvider(this, petsViewModelFactory)[PetsViewModel::class.java]
        petsViewModel.getPets(requireActivity())
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(binding.petsRecycler) {
            petsAdapter = PetsAdapter()
            adapter = petsAdapter
        }
        setupItemClicks()
    }

    private fun setupItemClicks() {
        petsAdapter.onItemClickListener = { item ->
            showBottomSheet(item)
        }
    }

    private fun showBottomSheet(pet: Pet) {
        val bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.bottom_sheet_layout)

        val petImage: ImageView? = bottomSheet.findViewById(R.id.petImage)
        val petDescr: TextView? = bottomSheet.findViewById(R.id.petDescr)
        val petYear: TextView? = bottomSheet.findViewById(R.id.petYear)
        val petFood: TextView? = bottomSheet.findViewById(R.id.petFood)
        val petFoodTV: TextView? = bottomSheet.findViewById(R.id.petFoodTV)
        if (petImage != null) {
            Glide.with(requireContext())
                .load(pet.photo)
                .apply(RequestOptions.bitmapTransform(CropSquareTransformation()))
                .into(petImage)
        }
        petDescr?.text = pet.description
        petYear?.text = pet.birthYear.toString()
        if (pet.favFoods != null) {
            petFoodTV?.visibility = View.VISIBLE
            petFood?.text = androidx.core.text.HtmlCompat.fromHtml(pet.favFoods.joinToString(separator = ", "), FROM_HTML_MODE_LEGACY).toString()
        }

        bottomSheet.show()

    }

}