package br.com.airescovit.clim.data

import android.content.Context
import br.com.airescovit.clim.data.db.DbHelper
import br.com.airescovit.clim.data.network.ApiHelper
import br.com.airescovit.clim.data.prefs.PreferenceHelper
import br.com.airescovit.clim.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Singleton
class AppDataManager @Inject constructor(@ApplicationContext context: Context, dbHelper: DbHelper, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : DataManager {

    private var mContext: Context
    private var mDbHelper: DbHelper
    private var mPreferencesHelper: PreferenceHelper
    private var mApiHelper: ApiHelper

    init {
        mContext = context
        mDbHelper = dbHelper
        mPreferencesHelper = preferenceHelper
        mApiHelper = apiHelper
    }

    override fun setUserAsLoggedOut() {
        updateUserInfo(null, null, DataManager.LoginMode.LOGGED_IN_MODE_LOGGED_OUT, null, null)
    }

    override fun updateUserInfo(accessToken: String?, userId: Long?, loginMode: DataManager.LoginMode, userName: String?, userEmail: String?) {
        setAccessToken(accessToken)
        setCurrentUserId(userId)
        setCurrentLoginMode(loginMode)
        setCurrentUserName(userName)
        setCurrentUserEmail(userEmail)

    }

    override fun getCurrentUserId(): Long {
        return mPreferencesHelper.getCurrentUserId()
    }

    override fun setCurrentUserId(id: Long?) {
        mPreferencesHelper.setCurrentUserId(id)
    }

    override fun getCurrentUserName(): String {
        return mPreferencesHelper.getCurrentUserName()
    }

    override fun setCurrentUserName(name: String?) {
        mPreferencesHelper.setCurrentUserName(name)
    }

    override fun setCurrentUserEmail(email: String?) {
        mPreferencesHelper.setCurrentUserEmail(email)
    }

    override fun getCurrentUserEmail(): String {
        return mPreferencesHelper.getCurrentUserEmail()
    }

    override fun setAccessToken(accessToken: String?) {
        mPreferencesHelper.setAccessToken(accessToken)
    }

    override fun getCurrentAccessToken(): String {
        return mPreferencesHelper.getCurrentAccessToken()
    }

    override fun getCurrentLoginMode(): Int {
        return mPreferencesHelper.getCurrentLoginMode()
    }

    override fun setCurrentLoginMode(loginMode: DataManager.LoginMode) {
        mPreferencesHelper.setCurrentLoginMode(loginMode)
    }

}