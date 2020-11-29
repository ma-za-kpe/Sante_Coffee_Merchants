package com.maku.santecoffeemerchants.data.repo

import androidx.lifecycle.LiveData
import com.maku.santecoffeemerchants.data.local.dao.BranchDao
import com.maku.santecoffeemerchants.data.local.dao.BranchResponseDao
import com.maku.santecoffeemerchants.data.local.dao.FarmerDao
import com.maku.santecoffeemerchants.data.local.entities.Branch
import com.maku.santecoffeemerchants.data.local.entities.BranchDetailsResponse
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import timber.log.Timber

class BranchRepoImpl(val dao: FarmerDao, val branchDao: BranchDao, val responseDao: BranchResponseDao) : BranchRepo {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    //start farmer
    override suspend fun getFarmerData(): LiveData<Farmer> {
        return dao.getFarmerDetails()
    }

    override suspend fun getFarmer(): LiveData<List<Farmer>> {
        return dao.getReminders()
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    override suspend fun insertSingleFarmer(farmer: Farmer) {
        dao.insert(farmer)
    }
    //end farmer

    //start branch
    override suspend fun getBranchData(): LiveData<Branch> {
      return branchDao.getBranchDetails()
    }

    override suspend fun insertBranch(branch: Branch) {
        branchDao.upsert(branch)
    }

    override suspend fun updateFarmerBranch(farmer: ArrayList<Farmer>, id: String) {
        branchDao.updateFarmerBranch(farmer, id)
    }

    override suspend fun updateFarmer(farmer: String, id: String) {
        branchDao.updateFarmer(farmer, id)
    }

    override suspend fun deleteAll() {
        branchDao.nukeTable()
    }

    override suspend fun getResponse(): LiveData<BranchDetailsResponse> {
        return responseDao.getResponse()
    }
    //end branch

}