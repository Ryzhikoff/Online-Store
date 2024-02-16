package com.example.catalog.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catalog.R
import com.example.catalog.databinding.FragmentCatalogBinding
import com.example.catalog.di.CatalogComponentProvider
import com.example.catalog.ui.CatalogViewModel
import com.example.catalog.ui.customview.TagView
import com.example.catalog.ui.rv.ProductAdapter
import com.example.core.interfaces.ProductAdapterItem
import com.example.catalog.ui.rv.SpacingItemDecoration
import com.example.catalog.utils.CatalogViewModelFactory
import com.example.core.models.ProductUi
import com.example.details.ui.DetailsFragment.Companion.KEY_PRODUCT_UI
import com.example.remote.models.doOnError
import com.example.remote.models.updateAdapterList
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter

    @Inject
    lateinit var viewModelFactory: CatalogViewModelFactory

    private val cashedItems = mutableListOf<ProductUi>()
    private val viewModel: CatalogViewModel by viewModels { viewModelFactory }

    // соответствует позиции в списке для спиннера
    private var currentTypeSort = -1

    private val tagViews: List<TagView> by lazy {
        with(binding) {
            listOf(
                tagViewAll,
                tagViewFace,
                tagViewMask,
                tagViewBody,
                tagViewSuntan
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity().application as CatalogComponentProvider)
            .getCatalogComponentProvider()
            .inject(this)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCatalogBinding.bind(view)


        viewModel.getProductList()
        initRecyclerView()
        initSpinner()
        setTagsClickListeners()
        resultListener()
    }

    private fun resultListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.apiResult.collect { result ->
                result.updateAdapterList {
                    @Suppress("UNCHECKED_CAST")
                    cashedItems.addAll(it as List<ProductUi>)
                    updateAdapterList()
                }
                result.doOnError {
                    println("Error response: $it")
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateAdapterList(products: List<ProductUi> = cashedItems) {
        productAdapter.submitList(products)
    }

    private val onFavoriteClickListener =
        object : ProductAdapter.OnFavoriteClickListener {
            override fun onClick(productUi: ProductUi) {
                if (productUi.isFavorite) {
                    viewModel.saveToFavorite(productUi)
                } else {
                    viewModel.deleteFromFavorites(productUi)
                }
            }
        }

    private val onItemClickListener =
        object : ProductAdapter.OnItemClickListener {
            override fun onClick(productUi: ProductUi) {
                navigateToDetails(productUi)
            }

        }

    private fun initRecyclerView() {

        productAdapter = ProductAdapter(this, onFavoriteClickListener, onItemClickListener)

        binding.recyclerView.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(
                SpacingItemDecoration(
                    paddingBottomInDp = MARGIN_AFTER_ITEMS_IN_DP,
                    paddingEndInDp = MARGIN_AFTER_ITEMS_IN_DP
                )
            )
        }

    }

    private fun navigateToDetails(productUi: ProductUi) {
        findNavController()
            .navigate(
                R.id.action_catalogFragment_to_detailsFragment,
                bundleOf(
                    Pair(KEY_PRODUCT_UI, productUi)
                )
            )
    }

    private fun setTagsClickListeners() = with(binding) {
        tagViewAll.setOnClickListener { processingClick(it as TagView) }
        tagViewFace.setOnClickListener { processingClick(it as TagView) }
        tagViewBody.setOnClickListener { processingClick(it as TagView) }
        tagViewSuntan.setOnClickListener { processingClick(it as TagView) }
        tagViewMask.setOnClickListener { processingClick(it as TagView) }
    }

    private fun processingClick(tagView: TagView) {
        tagViews.filter { it.tagName != tagView.tagName }.map { it.isSelected = false }
        tagView.isSelected = !tagView.isSelected


        if (tagView.tagName == binding.tagViewAll.tagName || tagViews.all { !it.isSelected }) {
            updateAdapterList(sortedList(items = cashedItems))
        } else {
            val filteredList = cashedItems.filter { it.tags.contains(tagView.tagName) }
            updateAdapterList(sortedList(items = filteredList))
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
        setSpinnerListener()
    }

    private fun setSpinnerListener() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                productAdapter.items?.let {
                    currentTypeSort = position
                    updateAdapterList(sortedList(position, it))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

    }

    private fun sortedList(typeSort: Int = currentTypeSort, items: List<ProductAdapterItem>): List<ProductUi> {
        val filteredList = items.filterIsInstance<ProductUi>()
        return when (typeSort) {
            0 -> filteredList.sortedByDescending { it.rating }
            1 -> filteredList.sortedByDescending { it.newPrice }
            2 -> filteredList.sortedBy { it.newPrice }
            else -> filteredList
        }
    }

    //    <item>По популярности</item>
//    <item>По уменьшению цены</item>
//    <item>По возрастанию цены</item>
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MARGIN_AFTER_ITEMS_IN_DP = 7
    }
}