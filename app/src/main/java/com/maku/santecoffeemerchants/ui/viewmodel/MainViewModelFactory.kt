package com.maku.santecoffeemerchants.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maku.santecoffeemerchants.data.repo.BranchRepo

//creates new instance of objects.
class MainViewModelFactory( private val repo: BranchRepo): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}
