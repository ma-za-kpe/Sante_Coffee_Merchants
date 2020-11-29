package com.maku.santecoffeemerchants.data.local.model


import com.google.gson.annotations.SerializedName

data class NationalIdNum(
    @SerializedName("card-no")
    val cardNo: String, // 000999888
    @SerializedName("date-of-expiry")
    val dateOfExpiry: String, // 12/11/2028
    @SerializedName("dob")
    val dob: String, // 11/11/1992
    @SerializedName("givenname")
    val givenname: String, // popopo
    @SerializedName("ID-num")
    val iDNum: String, // CHHHTTTYTYS&&&BS777
    @SerializedName("nationality")
    val nationality: String, // ugandan
    @SerializedName("sex")
    val sex: String, // F
    @SerializedName("surname")
    val surname: String // popo
)