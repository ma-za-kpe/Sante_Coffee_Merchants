package com.maku.santecoffeemerchants.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object DialogUtils {
    fun callFarmer(context: Context, phone: String?) {
        if (phone == null) {
            Toast.makeText(context, "No phone number provided", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        context.startActivity(intent)
    }
}