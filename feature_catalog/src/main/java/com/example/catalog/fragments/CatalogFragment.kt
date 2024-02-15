package com.example.catalog.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.catalog.R
import com.example.catalog.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCatalogBinding.bind(view)



        initSpinner()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.apply {
            tagView.setOnClickListener {
                tagView.isSelected = !tagView.isSelected
            }
        }
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sorted_list,
            R.layout.spinner_item
        ).also { arrayAdapter ->  
            binding.spinner.adapter = arrayAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}