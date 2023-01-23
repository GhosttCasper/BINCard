package com.example.bincard.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bincard.databinding.FragmentBinDetailBinding
import com.example.bincard.ui.viewmodel.BinViewModel

class BinDetailFragment : Fragment() {

    private val viewModel: BinViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBinDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.binDetailFragment = this@BinDetailFragment

        // Inflate the layout for this fragment
        return binding.root
    }

    fun launchMap(latitude: Int, longitude: Int) {
        val coordinates = "$latitude, $longitude"
        val gmmIntentUri = Uri.parse("geo:0,0?q=$coordinates")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}