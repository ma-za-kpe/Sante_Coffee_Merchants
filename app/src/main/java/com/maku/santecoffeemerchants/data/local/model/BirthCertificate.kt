package com.maku.santecoffeemerchants.data.local.model


import com.google.gson.annotations.SerializedName

data class BirthCertificate(
    @SerializedName("dob")
    val dob: String, // 11/11/1992
    @SerializedName("name")
    val name: String // popo
)