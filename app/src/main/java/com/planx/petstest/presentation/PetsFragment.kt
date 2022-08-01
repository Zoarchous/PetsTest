package com.planx.petstest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.planx.petstest.databinding.FragmentPetsBinding
import dagger.hilt.android.AndroidEntryPoint
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
    }

}