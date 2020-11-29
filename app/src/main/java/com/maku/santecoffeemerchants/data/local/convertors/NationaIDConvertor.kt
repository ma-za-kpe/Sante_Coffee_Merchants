package com.maku.santecoffeemerchants.data.local.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maku.santecoffeemerchants.data.local.model.BirthCertificate
import com.maku.santecoffeemerchants.data.local.model.NationalIdNum

class NationaIDConvertor {
    @TypeConverter
    fun nationalIdNumToString(nationalIdNum: NationalIdNum): String = Gson().toJson(nationalIdNum)

    @TypeConverter
    fun stringToNationalIdNum(string: String): NationalIdNum = Gson().fromJson(string, NationalIdNum::class.java)
}