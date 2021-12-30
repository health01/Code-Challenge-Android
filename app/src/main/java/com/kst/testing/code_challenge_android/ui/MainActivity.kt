package com.kst.testing.code_challenge_android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.kst.testing.code_challenge_android.databinding.ActivityMainBinding
import com.kst.testing.code_challenge_android.network.UIState
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            mainViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UIState.Success -> {
                        uiState.average.toString().let {
                            binding.textviewAverage.text = it
                        }
                    }
                    is UIState.Error -> {
                        uiState.exception?.let { exception ->
                            Toast.makeText(
                                this@MainActivity,
                                exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }
            }
        }

        mainViewModel.getNewData()
    }
}