package br.com.airescovit.clim.data

import android.content.Context
import br.com.airescovit.clim.data.db.DbHelper
import br.com.airescovit.clim.data.network.ApiHelper
import br.com.airescovit.clim.data.network.Single
import br.com.airescovit.clim.data.network.login.model.LoginResponse
import br.com.airescovit.clim.data.network.model.login.model.LoginRequest
import br.com.airescovit.clim.data.network.model.login.model.RegisterRequest
import br.com.airescovit.clim.data.prefs.PreferenceHelper
import br.com.airescovit.clim.di.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Singleton
class AppDataManager @Inject constructor(@ApplicationContext context: Context, dbHelper: DbHelper, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : DataManager {


    private var mContext: Context = context
    private var mDbHelper: DbHelper = dbHelper
    private var mPreferencesHelper: PreferenceHelper = preferenceHelper
    private var mApiHelper: ApiHelper = apiHelper

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

    override fun doLoginRequest(loginRequest: LoginRequest): Observable<LoginResponse> {
        return mApiHelper.doLoginRequest(loginRequest)
    }

    override fun doRegisterRequest(registerRequest: Single<RegisterRequest>): Observable<LoginResponse> {
        return mApiHelper.doRegisterRequest(registerRequest)
    }

}