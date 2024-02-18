package com.example.feature_favorites.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.models.ProductUi
import com.example.core.ui.product_rv.ProductAdapter
import com.example.core.ui.product_rv.SpacingItemDecoration
import com.example.details.ui.DetailsFragment.Companion.KEY_PRODUCT_UI
import com.example.feature_favorites.R
import com.example.feature_favorites.di.FavoritesComponentProvider
import com.example.feature_favorites.ui.ProductViewModel
import com.example.feature_favorites.utils.ProductsViewModelFactory
import com.example.remote.models.doOnError
import com.example.remote.models.updateAdapterList
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsFragment : Fragment(R.layout.fragment_products) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val cashedItems = mutableListOf<ProductUi>()

    @Inject
    lateinit var viewModelFactory: ProductsViewModelFactory
    private val viewModel: ProductViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity().application as FavoritesComponentProvider)
            .getFavoritesComponent()
            .inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        arguments?.getParcelableArrayListCompat<ProductUi>(KEY_PRODUCTS_LIST)?.let {
//            cashedItems.addAll(it)
//        }
        recyclerView = view.findViewById(R.id.recyclerView)
        viewModel.getProductList()
        initRecyclerView()
        resultListener()
    }

    private fun resultListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.apiResult.collect { result ->
                result.updateAdapterList {
                    view?.findViewById<ProgressBar>(R.id.progressBar)?.isVisible = false
                    cashedItems.clear()
                    @Suppress("UNCHECKED_CAST")
                    cashedItems.addAll(it as List<ProductUi>)
                    productAdapter.submitList(it)
                }
                result.doOnError {
                    println("Error response: $it")
                }
            }
        }
    }



    private val onFavoriteClickListener =
        object : ProductAdapter.OnFavoriteClickListener {
            override fun onClick(productUi: ProductUi) {
                removeAndUpdateAdapterList(productUi)
            }
        }

    private fun removeAndUpdateAdapterList(productUi: ProductUi) {
        viewModel.deleteFromFavorites(productUi)
        val position = productAdapter.items!!.indexOf(productUi)
        cashedItems.remove(productUi)
        productAdapter.items = cashedItems
        productAdapter.notifyItemRemoved(position)
    }

    private val onItemClickListener =
        object : ProductAdapter.OnItemClickListener {
            override fun onClick(productUi: ProductUi) {
                navigateToDetails(productUi)
            }
        }

    private fun navigateToDetails(productUi: ProductUi) {
        findNavController()
            .navigate(
                R.id.action_favoritesFragment_to_detailsFragment,
                bundleOf(
                    Pair(KEY_PRODUCT_UI, productUi)
                )
            )
    }

    private fun initRecyclerView() {
        productAdapter = ProductAdapter(this, onFavoriteClickListener, onItemClickListener)

        recyclerView.apply {
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

    companion object {
//        const val KEY_PRODUCTS_LIST = "items_list"
        const val MARGIN_AFTER_ITEMS_IN_DP = 7
    }
}