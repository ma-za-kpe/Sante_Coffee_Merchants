package com.maku.santecoffeemerchants.data.local.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maku.santecoffeemerchants.data.local.entities.Farmer

class ListConvertor {

    @TypeConverter
    fun listToJson(value: List<Farmer>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Farmer>::class.java).toList()
}