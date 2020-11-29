package com.maku.santecoffeemerchants.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.maku.santecoffeemerchants.data.local.entities.BranchDetailsResponse
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import com.maku.santecoffeemerchants.data.local.entities.Farmer_ID
import com.maku.santecoffeemerchants.data.local.entities.Response_ID

@Dao
interface BranchResponseDao {
    @Query("select * from branch_response where id = $Response_ID")
    fun getResponse(): LiveData<BranchDetailsResponse>
}