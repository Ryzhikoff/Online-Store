package com.example.onlinestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.NavigationManager
import com.example.onlinestore.databinding.ActivityMainBinding
import com.example.onlinestore.di.AppComponentProvider
import com.example.setting_provider.SettingProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationManager {

    private val binding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navHostFragment.navController
    }

    @Inject
    lateinit var settingProvider: SettingProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        (application as AppComponentProvider).getAppComponent()
            .inject(this)

        if (savedInstanceState == null) {
            if (settingProvider.isRegistrationComplete()) {
                startBottomNavigation()
            } else {
                navController.setGraph(com.example.registration.R.navigation.registration_graph)
            }
        }
    }

    override fun startBottomNavigation() {
        navController.setGraph(R.navigation.main_nav_graph)
        binding.bottomNavigationView.apply {
            isVisible = true
            setupWithNavController(navController)
        }
    }

}