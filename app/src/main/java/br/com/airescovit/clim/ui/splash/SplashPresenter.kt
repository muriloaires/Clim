package br.com.airescovit.clim.ui.splash

import android.os.Handler
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Logics on 12/01/2018.
 */
class SplashPresenter<V : SplashMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), SplashMvpPresenter<V> {

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)
        Handler().postDelayed({
            decideNextActivity()
        }, 1500)
    }

    private fun decideNextActivity() {
        if (dataManager.getCurrentLoginMode() == DataManager.LoginMode.LOGGED_IN_MODE_LOGGED_OUT.mType) {
            getMvpView()!!.openLoginActivity()
        } else {
            getMvpView()!!.openMainActivity()
        }

        getMvpView()!!.finish()

    }

}