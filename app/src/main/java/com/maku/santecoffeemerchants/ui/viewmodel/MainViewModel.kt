package com.maku.santecoffeemerchants.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maku.santecoffeemerchants.data.local.entities.Branch
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import com.maku.santecoffeemerchants.data.repo.BranchRepo
import com.maku.santecoffeemerchants.internal.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (var repo: BranchRepo) : ViewModel() {

    // TODO: 11/27/2020 fix this... before sending, also dont forget to rename classes appropriately when cleaning up

    //called only when needed - lazily

    //start all response
    val response by lazyDeferred {
        repo.getResponse()
    }
    //end all response

    //start farmer
    val farmers by lazyDeferred {
        repo.getFarmerData()
    }

    val farmer by lazyDeferred {
        repo.getFarmer()
    }

    fun addFarmer(farmer: Farmer) = viewModelScope.launch(Dispatchers.IO) {
        repo.insertSingleFarmer(farmer)
    }
    //end farmer

    //start branch, TODO: note that branch is added only once, so find a way to clock any further additions to a new branch name
    val branch by lazyDeferred {
        repo.getBranchData()
    }

    fun addBranch(branch: Branch) = viewModelScope.launch(Dispatchers.IO) {
        repo.insertBranch(branch)
    }

    fun updateFarmerin(farmer: ArrayList<Farmer>, id: String)= viewModelScope.launch(Dispatchers.IO){
        repo.updateFarmerBranch(farmer, id)
    }

    fun updateFarmerinBranch(farmer: String, id: String)= viewModelScope.launch(Dispatchers.IO){
        repo.updateFarmer(farmer, id)
    }

    suspend fun delete(){
        repo.deleteAll()
    }
    //end branch

}