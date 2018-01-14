package br.com.airescovit.clim.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Logics on 11/01/2018.
 */
class NetworkUtils {
    companion object {
        fun isNetworkConnected(applicationContext: Context?): Boolean {
            val cm = applicationContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

    }
}