package com.maku.santecoffeemerchants.data.local.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maku.santecoffeemerchants.data.local.entities.Farmer

class FarmerConvertor {
    @TypeConverter
    fun farmerToString(farmer: Farmer): String = Gson().toJson(farmer)

    @TypeConverter
    fun stringToFarmer(string: String): Farmer = Gson().fromJson(string, Farmer::class.java)
}