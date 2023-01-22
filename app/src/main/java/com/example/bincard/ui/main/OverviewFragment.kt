package com.example.bincard.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bincard.R
import com.example.bincard.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    // Binding object instance with access to the views in the game_fragment.xml layout
    private lateinit var binding: FragmentOverviewBinding
    private val viewModel: BinViewModel by activityViewModels()

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
        viewModel.getBin(binInput)
        findNavController()
            .navigate(R.id.action_overviewFragment_to_binDetailFragment)
    }
}