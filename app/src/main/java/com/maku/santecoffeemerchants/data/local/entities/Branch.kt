package com.maku.santecoffeemerchants.data.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.maku.santecoffeemerchants.data.local.model.Manager

const val BRANCH_ID = 0
@Entity(tableName = "branch_details")
data class Branch(
    @SerializedName("farmers")
    val farmers: ArrayList<Farmer>,
    @SerializedName("id")
    val id: String, // BRNCH001
    @SerializedName("manager")
    val manager: Manager,
    @SerializedName("name")
    val name: String // BRANCH01
){
    @PrimaryKey(autoGenerate = false)
    var bid: Int = BRANCH_ID
}