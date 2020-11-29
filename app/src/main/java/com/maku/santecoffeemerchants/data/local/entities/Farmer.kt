package com.maku.santecoffeemerchants.data.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.maku.santecoffeemerchants.data.local.model.BirthCertificate
import com.maku.santecoffeemerchants.data.local.model.NationalIdNum

const val Farmer_ID = 0
@Entity(tableName = "farmer_details")
data class Farmer(
        @SerializedName("birth-certificate")
        val birthCertificate: BirthCertificate,
        @SerializedName("national-id-num")
        val nationalIdNum: NationalIdNum,
        @SerializedName("phone-number")
        val phoneNumber: String // +25677823456
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = Farmer_ID
}