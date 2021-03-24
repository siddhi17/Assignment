package com.storepanel.utils

import android.content.Context
import android.net.ConnectivityManager

/*Check if internet connection in available*/
object NetworkUtils {

    fun isInternetAvailable(context: Context): Boolean {
        var status:Boolean=false
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            status=netInfo != null && netInfo.isConnected

            return status
        } catch (e: NullPointerException) {
            e.printStackTrace()
            return false
        }
    }
}