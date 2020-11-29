package com.maku.santecoffeemerchants.ui.framer_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.maku.santecoffeemerchants.R
import com.maku.santecoffeemerchants.databinding.FragmentFarmerDetailsBinding
import com.maku.santecoffeemerchants.databinding.FragmentFarmersListBinding

class FarmerDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFarmerDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_farmer_details, container, false
        )
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.no.text = arguments?.getString("details")
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FarmerDetailsFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}