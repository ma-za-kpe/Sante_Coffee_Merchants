package com.maku.santecoffeemerchants.data.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.maku.santecoffeemerchants.data.local.entities.Branch
const val Response_ID = 0
@Entity(tableName = "branch_response")
data class BranchDetailsResponse(
    @SerializedName("Branch")
    val branch: Branch
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = Response_ID
}
