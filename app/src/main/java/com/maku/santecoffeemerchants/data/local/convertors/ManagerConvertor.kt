package com.maku.santecoffeemerchants.data.local.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maku.santecoffeemerchants.data.local.entities.Branch
import com.maku.santecoffeemerchants.data.local.model.Manager

class ManagerConvertor {
    @TypeConverter
    fun managerToString(branch: Manager): String = Gson().toJson(branch)

    @TypeConverter
    fun stringToManager(string: String): Manager = Gson().fromJson(string, Manager::class.java)
}