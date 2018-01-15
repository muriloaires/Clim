package br.com.airescovit.clim.data.prefs

import android.content.Context
import android.content.SharedPreferences
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.di.ApplicationContext
import br.com.airescovit.clim.di.PreferenceInfo
import br.com.airescovit.clim.utils.AppConstants
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Singleton
class AppPreferenceHelper @Inject constructor(@ApplicationContext context: Context, @PreferenceInfo prefFileName: String) : PreferenceHelper {

    private var mPref: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    private val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
    private val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
    private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
    private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
    private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"

    override fun getCurrentUserId(): Long {
        val userId: Long? = mPref.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX)
        return if (userId == null) AppConstants.NULL_INDEX else userId
    }

    override fun setCurrentUserId(id: Long?) {
        val userId: Long = id ?: AppConstants.NULL_INDEX
        mPref.edit().putLong(PREF_KEY_CURRENT_USER_ID, userId).apply()
    }

    override fun getCurrentUserName(): String {
        return mPref.getString(PREF_KEY_CURRENT_USER_NAME, null)
    }

    override fun setCurrentUserName(name: String?) {
        mPref.edit().putString(PREF_KEY_CURRENT_USER_NAME, name).apply()
    }

    override fun setCurrentUserEmail(email: String?) {
        mPref.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()
    }

    override fun getCurrentUserEmail(): String {
        return mPref.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
    }

    override fun setAccessToken(accessToken: String?) {
        mPref.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }

    override fun getCurrentAccessToken(): String {
        return mPref.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    override fun setCurrentLoginMode(loginMode: DataManager.LoginMode) {
        mPref.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, loginMode.mType).apply()
    }

    override fun getCurrentLoginMode(): Int {
        return mPref.getInt(PREF_KEY_USER_LOGGED_IN_MODE, DataManager.LoginMode.LOGGED_IN_MODE_LOGGED_OUT.mType)
    }
}