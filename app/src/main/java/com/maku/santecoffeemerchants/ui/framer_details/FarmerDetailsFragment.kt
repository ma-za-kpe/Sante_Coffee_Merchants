package com.maku.santecoffeemerchants.ui.framer_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.maku.santecoffeemerchants.R
import com.maku.santecoffeemerchants.databinding.FragmentFarmerDetailsBinding
import timber.log.Timber
import java.util.*

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

        val farmer = arguments?.getString("details")
        val list = arrayListOf(farmer)
        val elephantList: MutableList<List<String>> = Arrays.asList(farmer?.split(","))

        // TODO: 11/30/2020 : code clean up required here too.. 
        Timber.d("farmerrr " + elephantList)
        for (i in 0 until elephantList.size) {
            Timber.d("farmer list  " + elephantList[0][0])
            binding.name.text = elephantList[0][0]
            binding.dob.text = elephantList[0][1]
            binding.cardno.text = elephantList[0][2]
            binding.date.text = elephantList[0][3]
            binding.dobb.text = elephantList[0][4]
            binding.givennam.text = elephantList[0][5]
            binding.idnum.text = elephantList[0][6]
            binding.name.text = elephantList[0][7]
            binding.sexx.text = elephantList[0][8]
            binding.srunnamee.text = elephantList[0][9]
            binding.phonee.text = elephantList[0][10]
        }
        
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