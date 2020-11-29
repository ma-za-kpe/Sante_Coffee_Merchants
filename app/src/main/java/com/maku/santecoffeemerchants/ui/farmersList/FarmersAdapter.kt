package com.maku.santecoffeemerchants.ui.farmersList

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import com.maku.santecoffeemerchants.databinding.FarmerRowBinding
import timber.log.Timber

class FarmersAdapter(private val farmerList: ArrayList<Farmer>) :
    RecyclerView.Adapter<FarmersAdapter.FarmerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FarmerRowBinding.inflate(inflater, parent, false)
        return FarmerViewHolder(binding)
    }

    override fun getItemCount() = farmerList.size

    override fun onBindViewHolder(holder: FarmerViewHolder, position: Int) {
        holder.bind(farmerList[position])
    }

    class FarmerViewHolder(rowBinding: FarmerRowBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {
        private val binding = rowBinding

        fun bind(farmer: Farmer) {
            Timber.d("phone ${farmer}")
            binding.farmerBinding = farmer
            binding.editTextTextBrandName.text = farmer.nationalIdNum.givenname
            binding.executePendingBindings()
            binding.editTextTextBrandName.setOnClickListener { view ->
                Timber.d("phone has been clicked")

                //pass the 'context' here
                val alertDialog = AlertDialog.Builder(view.context)
                alertDialog.setTitle("Rover Name: "+ farmer.nationalIdNum.givenname)
                alertDialog.setPositiveButton("Close") { dialog, which -> dialog.cancel() }

                val dialog = alertDialog.create()
                dialog.show()

            }
        }
    }
}