package com.example.bincard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bincard.BaseApplication
import com.example.bincard.R
import com.example.bincard.data.BinDatabase
import com.example.bincard.databinding.FragmentOverviewBinding
import com.example.bincard.ui.adapter.BinListAdapter
import com.example.bincard.ui.adapter.BinListener
import com.example.bincard.ui.viewmodel.BinViewModel

const val MAX_SIZE_BIN = 8
const val MIN_SIZE_BIN = 6

class OverviewFragment : Fragment() {

    // Binding object instance with access to the views in the game_fragment.xml layout
    private lateinit var binding: FragmentOverviewBinding
    private val viewModel: BinViewModel by activityViewModels() {
        BinViewModelFactory(
            (activity?.application as BaseApplication).database
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = BinListAdapter(BinListener { bin ->
            viewModel.onBinClicked(bin)
            findNavController()
                .navigate(R.id.action_overviewFragment_to_binDetailFragment)
        })

        // Setup a click listener for the Submit button.
        binding.submit.setOnClickListener { onSubmitBin() }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun onSubmitBin() {
        val binInput = binding.textInputEditText.text.toString()
        if (isInputCorrect(binInput)) {
            setErrorTextField(false)
            viewModel.getBin(binInput)
            findNavController()
                .navigate(R.id.action_overviewFragment_to_binDetailFragment)
        } else
            setErrorTextField(true)
    }

    fun isInputCorrect(input: String): Boolean {
        if (input.length in MIN_SIZE_BIN..MAX_SIZE_BIN
            && input.matches(Regex("\\d+"))
        ) {
            return true
        }
        return false
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class BinViewModelFactory(private val binDatabase: BinDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BinViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BinViewModel(binDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}