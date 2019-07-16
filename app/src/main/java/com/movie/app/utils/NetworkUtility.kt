package com.movie.app.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.regex.Pattern

/**
 * If your application performs a lot of network operations,
 * you should provide user settings to check whether you have internet connection or not
 * Created by hemendrag on 16/07/2019.
 */
class NetworkUtility() {

    /**
     * singleton for NetworkUtility reference
     */
    companion object{

        private var instance: NetworkUtility? = null
        fun getInstance(): NetworkUtility {
            if (instance == null) {
                instance = NetworkUtility()
            }
            return instance as NetworkUtility
        }
    }


    /**
     * Checking for all possible internet providers
     */
    fun isConnectingToInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networks = connectivityManager.allNetworks
            var networkInfo: NetworkInfo
            for (mNetwork in networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork)
                if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        } else {
            if (connectivityManager != null) {
                val info = connectivityManager.allNetworkInfo
                if (info != null) {
                    for (anInfo in info) {
                        if (anInfo.state == NetworkInfo.State.CONNECTED) {
                            Log.d("Network", "NETWORKNAME: " + anInfo.typeName)
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    /**
     * show response pop up in case if internet is not connected
     */
    var dialog: AlertDialog? = null

    fun showPopUp( mContext: Context, responseStr: String) {

        val builder = AlertDialog.Builder(mContext, android.R.style.Theme_Material_Light_Dialog)
        builder.setMessage(responseStr)
        builder.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss()
        }
        try {
            if (dialog != null && dialog!!.isShowing) {
                dialog!!.dismiss()
            }
            dialog = builder.create()
            dialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
