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
        }

        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Your custom logic here
                if ( binding.stateListContainer.visibility == View.GONE) {
                    setViewsVisibility(true)
                    // Handle the back press event
                    // Allow the default back press behavior
                }else{
                    finish()
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
    override fun onDestroy() {
        super.onDestroy()
        // Remove the callback when the activity is destroyed
        backPressedCallback?.remove()
    }


}


