package com.maku.santecoffeemerchants.data.local.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maku.santecoffeemerchants.data.local.entities.Branch

class BranchConvertor {

    @TypeConverter
    fun branchToString(branch: Branch): String = Gson().toJson(branch)

    @TypeConverter
    fun stringToBranch(string: String): Branch = Gson().fromJson(string, Branch::class.java)

}