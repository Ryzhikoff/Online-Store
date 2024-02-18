package com.example.account.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.account.R
import com.example.account.databinding.FragmentAccountBinding
import com.example.account.di.AccountComponentProvider
import com.example.account.ui.AccountViewModel
import com.example.account.utils.AccountViewModelFactory
import com.example.core.extensions.formEnding
import com.example.core.interfaces.NavigationManager
import com.example.setting_provider.SettingProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountFragment: Fragment(R.layout.fragment_account) {

    private var _binding: FragmentAccountBinding? = null
    private val binding: FragmentAccountBinding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: AccountViewModelFactory
    @Inject
    lateinit var settingProvider: SettingProvider

    private val viewModel: AccountViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity().application as AccountComponentProvider)
            .getAccountComponent()
            .inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAccountBinding.bind(view)

        viewModel.getCountElement()
        getCountElement()
        setData()
        setClickListener()
    }

    private fun setClickListener()= with(binding){
        exitButton.setOnClickListener {
            viewModel.clearData()
            (requireActivity() as NavigationManager)
                .startRegistrationNavigation()
        }

        favorites.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_favorites_graph)
        }

    }

    private fun getCountElement() {
        lifecycleScope.launch {
            viewModel.countElement.collect { count ->
                if (count > 0) {
                    setCountProducts(count)
                }
            }
        }
    }

    private fun setCountProducts(count: Long) {
        val countStr = count.toString()
        binding.favorites.setContent(
            extraText = "$countStr ${countStr.last().formEnding(resources, R.array.products)}"
        )
    }

    private fun setData() = with(binding) {
        val userData = settingProvider.getUserData()
        person.setContent(
            title = "${userData.name} ${userData.surname}",
            extraText = userData.phoneNumber
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}