package com.example.carfaxandroidtechnicalassignment.utils

import android.content.Intent
import android.net.Uri

object Utils {
    fun startCall(context: android.content.Context, phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        context.startActivity(callIntent)
    }
}