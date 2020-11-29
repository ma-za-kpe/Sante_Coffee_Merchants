package com.maku.santecoffeemerchants.data.repo

import androidx.lifecycle.LiveData
import com.maku.santecoffeemerchants.data.local.entities.Branch
import com.maku.santecoffeemerchants.data.local.entities.BranchDetailsResponse
import com.maku.santecoffeemerchants.data.local.entities.Farmer

interface BranchRepo {
        // suspend enables us to call the function from a coroutine

        //farmer
        suspend fun getFarmerData(): Farmer
        suspend fun getFarmer(): List<Farmer>
        suspend fun insertSingleFarmer(farmer: Farmer)

        //branch
        suspend fun getBranchData(): LiveData<Branch>
        suspend fun insertBranch(branch: Branch)
        suspend fun updateFarmerBranch(farmer: ArrayList<Farmer>, id: String)
        suspend fun updateFarmer(nom: String, id: String)

        // delete all tables
        suspend fun deleteAll()

        //response
        suspend fun getResponse(): LiveData<BranchDetailsResponse>

}