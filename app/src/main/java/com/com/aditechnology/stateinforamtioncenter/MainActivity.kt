package com.com.aditechnology.stateinforamtioncenter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.com.aditechnology.stateinforamtioncenter.network.StateApiImpl
import com.com.aditechnology.stateinforamtioncenter.repositary.StateRepository
import com.com.aditechnology.stateinforamtioncenter.viewmodel.SharedStateViewModel
import com.com.aditechnology.stateinforamtioncenter.viewmodel.StateViewModelFactory

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                Log.e("TAG",""+searchText)
                viewModel.filterStateList(searchText)

            }
        })

        val searchButton: Button = findViewById(R.id.button_search)
        searchButton.setOnClickListener {
            findViewById<View>(R.id.state_list_container).visibility = View.GONE
            findViewById<View>(R.id.state_filter_container).visibility = View.GONE
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.state_detail_container) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.stateDetailScreenFragment)


        }
    }
    override fun onBackPressed() {
        // Get the NavController for state_detail_container

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.state_detail_container) as NavHostFragment
        val navController = navHostFragment.navController

        // Check if we're currently on stateDetailScreenFragment
        if (navController.currentDestination?.id == R.id.stateDetailScreenFragment) {
            // Restore visibility of state_list_container and state_filter_container
            findViewById<View>(R.id.state_list_container).visibility = View.VISIBLE
            findViewById<View>(R.id.state_filter_container).visibility = View.VISIBLE
        }

        // Call the superclass to handle the back press
        super.onBackPressed()
    }

}