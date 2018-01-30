package br.com.airescovit.clim.utils

import android.util.Patterns


/**
 * Created by murilo aires on 23/01/2018.
 */
class TextUtils {
    companion object {
        fun validEmail(email: String): Boolean {
            val pattern = Patterns.EMAIL_ADDRESS
            return pattern.matcher(email).matches()
        }
    }
}