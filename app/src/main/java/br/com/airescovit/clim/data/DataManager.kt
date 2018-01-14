package br.com.airescovit.clim.data

import br.com.airescovit.clim.data.db.DbHelper
import br.com.airescovit.clim.data.network.ApiHelper
import br.com.airescovit.clim.data.prefs.PreferenceHelper

/**
 * Created by Logics on 12/01/2018.
 */
interface DataManager : DbHelper, PreferenceHelper, ApiHelper {

    fun setUserAsLoggedOut()
    fun updateUserInfo(accessToken: String?, userId: Long?, loginMode: LoginMode, userName: String?, userEmail: String?)
    enum class LoginMode(type: Int) {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGE_IN_MODE_SERVER(1);

        var mType: Int = type

    }
}