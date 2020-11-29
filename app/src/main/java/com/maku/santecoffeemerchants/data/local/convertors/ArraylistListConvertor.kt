package com.maku.santecoffeemerchants.data.local.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maku.santecoffeemerchants.data.local.entities.Farmer
import java.lang.reflect.Type

object ArraylistListConvertor {
    @TypeConverter
    fun fromString(value: String?): ArrayList<Farmer> {
        val listType: Type = object : TypeToken<ArrayList<Farmer?>?>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Farmer?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}