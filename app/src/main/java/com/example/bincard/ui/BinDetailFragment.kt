package com.example.bincard.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bincard.BaseApplication
import com.example.bincard.R
import com.example.bincard.databinding.FragmentBinDetailBinding
import com.example.bincard.ui.viewmodel.BinApiStatus
import com.example.bincard.ui.viewmodel.BinViewModel
import com.example.bincard.ui.viewmodel.BinViewModelFactory

/**
 * [BinDetailFragment] displays the details of the selected item.
 */
class BinDetailFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
    private val viewModel: BinViewModel by activityViewModels() {
        BinViewModelFactory(
            (activity?.application as BaseApplication).database
        )
    }

    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment
    private var _binding: FragmentBinDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBinDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.binDetailFragment = this@BinDetailFragment

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observer for the no data.
        viewModel.status.observe(viewLifecycleOwner) { status ->
            if (status == BinApiStatus.NO_DATA) {
                showNoDataToast()
            }
        }

    }

    /**
     * Method for displaying a Toast no found message.
     */
    private fun showNoDataToast() {
        Toast.makeText(activity, getString(R.string.no_found), Toast.LENGTH_LONG).show()
    }

    fun launchMap(latitude: Int, longitude: Int) {
        val coordinates = "$latitude, $longitude"
        val gmmIntentUri = Uri.parse("geo:0,0?q=$coordinates")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    fun launchContract(phone: String) {
        val contactIntentUri = Uri.parse("tel:${phone.trim()}")
        val callIntent = Intent(Intent.ACTION_DIAL, contactIntentUri)
        startActivity(callIntent)
    }

    fun openWebsite(url: String) {
        val queryUrl: Uri = Uri.parse("https://${url.trim()}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        startActivity(intent)
    }

    /**
     * Called when fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.resetBin()
    }
}