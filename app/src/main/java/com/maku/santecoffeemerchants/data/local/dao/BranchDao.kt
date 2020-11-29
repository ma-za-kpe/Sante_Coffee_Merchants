package com.maku.santecoffeemerchants.data.local.dao

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maku.santecoffeemerchants.data.local.entities.BRANCH_ID
import com.maku.santecoffeemerchants.data.local.entities.Branch
import com.maku.santecoffeemerchants.data.local.entities.Farmer


@Dao
interface BranchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(branch: Branch) //update or insert at the same time

    @Query("select * from branch_details where bid = $BRANCH_ID")
    fun getBranchDetails(): LiveData<Branch>

    /**
     * Updating only farmer
     * By order id
     */

    @Query("UPDATE branch_details SET farmers=:farmers WHERE id = :id")
    fun updateFarmerBranch(farmers: ArrayList<Farmer>?, id: String)

    @Query("UPDATE branch_details SET name=:name WHERE id = :id")
    fun updateFarmer(name: String?, id: String)

    @Query("DELETE FROM branch_details")
    fun nukeTable()

}
