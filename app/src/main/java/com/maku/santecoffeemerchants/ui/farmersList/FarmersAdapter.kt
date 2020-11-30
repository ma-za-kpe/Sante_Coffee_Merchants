package com.maku.santecoffeemerchants.ui.farmersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import com.maku.santecoffeemerchants.databinding.FarmerRowBinding
import timber.log.Timber

class FarmersAdapter(
        private val farmerList: ArrayList<Farmer>,
        val callFarmer: (Any) -> Unit,
        val detailsOfFarmer: (Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any?) -> Unit) :
    RecyclerView.Adapter<FarmersAdapter.FarmerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FarmerRowBinding.inflate(inflater, parent, false)
        return FarmerViewHolder(binding)
    }

    override fun getItemCount() = farmerList.size

    override fun onBindViewHolder(holder: FarmerViewHolder, position: Int) {
        holder.bind(farmerList[position], callFarmer, detailsOfFarmer)
    }

    class FarmerViewHolder(rowBinding: FarmerRowBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        private val binding = rowBinding

        fun bind(farmer: Farmer, callFarmer: (Any) -> Unit, detailsOfFarmer: (Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any?) -> Unit) {
            Timber.d("phone ${farmer}")
            binding.farmerBinding = farmer
            binding.editTextTextBrandName.text = farmer.nationalIdNum.givenname
            binding.executePendingBindings()
            binding.editTextTextBrandName.setOnClickListener { view ->
                Timber.d("phone has been clicked")

                //send data to new details screen
                detailsOfFarmer(farmer.birthCertificate.name,farmer.birthCertificate.dob, farmer.nationalIdNum.cardNo, farmer.nationalIdNum.dateOfExpiry, farmer.nationalIdNum.dob, farmer.nationalIdNum.givenname, farmer.nationalIdNum.iDNum, farmer.nationalIdNum.nationality, farmer.nationalIdNum.sex, farmer.nationalIdNum.surname, farmer.phoneNumber)

                //pass the 'context' here
//                val alertDialog = AlertDialog.Builder(view.context)
//                alertDialog.setTitle("Rover Name: "+ farmer.nationalIdNum.givenname)
//                alertDialog.setPositiveButton("Close") { dialog, which -> dialog.cancel() }
//
//                val dialog = alertDialog.create()
//                dialog.show()

            }

            binding.callImage.setOnClickListener {
                val phone = farmer.phoneNumber
                callFarmer(phone)
            }
        }
    }
}