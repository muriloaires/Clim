package br.com.airescovit.clim.data.prefs

import br.com.airescovit.clim.data.DataManager

/**
 * Created by Logics on 12/01/2018.
 */
interface PreferenceHelper {
    fun getCurrentUserId(): Long
    fun setCurrentUserId(id: Long?)
    fun getCurrentUserName(): String
    fun setCurrentUserName(name: String?)
    fun setCurrentUserEmail(email: String?)
    fun getCurrentUserEmail(): String
    fun setAccessToken(accessToken: String?)
    fun getCurrentAccessToken(): String
    fun setCurrentLoginMode(loginMode: DataManager.LoginMode)
    fun getCurrentLoginMode(): Int
}