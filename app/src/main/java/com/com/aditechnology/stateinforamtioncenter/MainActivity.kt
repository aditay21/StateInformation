package com.com.aditechnology.stateinforamtioncenter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.com.aditechnology.stateinforamtioncenter.databinding.ActivityMainBinding
import com.com.aditechnology.stateinforamtioncenter.network.StateApiImpl
import com.com.aditechnology.stateinforamtioncenter.repositary.StateRepository
import com.com.aditechnology.stateinforamtioncenter.viewmodel.SharedStateViewModel
import com.com.aditechnology.stateinforamtioncenter.viewmodel.StateViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var backPressedCallback: OnBackPressedCallback? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Bind the ViewModel to the layout
        binding.lifecycleOwner = this

        val repository = StateRepository(StateApiImpl())
        val viewModel = ViewModelProvider(this,
            StateViewModelFactory(repository))[SharedStateViewModel::class.java]

        val  editTextSearch: EditText = findViewById(R.id.edit_text_search)

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                // Handle text change
                val searchText = editTextSearch.text.toString()
                // Perform action after text change
                viewModel.filterStateList(searchText)

            }
        })

        val searchButton: Button = findViewById(R.id.button_search)
        searchButton.setOnClickListener {
            setViewsVisibility(false)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.state_detail_container) as NavHostFragment
           navHostFragment.navController.also {
                it.navigate(R.id.stateDetailScreenFragment)
            }


        }

        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Your custom logic here
                if (!shouldHandleBackPress()) {
                    // Handle the back press event
                    // Allow the default back press behavior
                    isEnabled = false // Disable the callback to allow the default behavior
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }

        // Add the callback to the OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, backPressedCallback as OnBackPressedCallback)
    }

    private fun setViewsVisibility(isVisible: Boolean) {
        val visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.stateListContainer.visibility = visibility
        binding.stateFilterContainer.visibility = visibility
        binding.editTextSearch.visibility = visibility
        binding.buttonSearch.visibility = visibility
    }

    // Method to determine whether to handle back press
    private fun shouldHandleBackPress(): Boolean {
        // Add your custom logic to determine if the back press should be handled
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.state_detail_container) as NavHostFragment
        val navController = navHostFragment.navController

        // Check if we're currently on stateDetailScreenFragment
        if (navController.currentDestination?.id == R.id.stateDetailScreenFragment) {
            // Restore visibility of state_list_container and state_filter_container
            setViewsVisibility(true)


        }
        return true;
    }
    override fun onDestroy() {
        super.onDestroy()
        // Remove the callback when the activity is destroyed
        backPressedCallback?.remove()
    }


}


