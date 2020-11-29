package com.maku.santecoffeemerchants.data.local.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maku.santecoffeemerchants.data.local.model.BirthCertificate

class BirthCertificateConvertor {

    @TypeConverter
    fun birthCertificateToString(birthCertificate: BirthCertificate): String = Gson().toJson(birthCertificate)

    @TypeConverter
    fun stringToBirthCertificate(string: String): BirthCertificate = Gson().fromJson(string, BirthCertificate::class.java)

}