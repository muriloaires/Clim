package br.com.airescovit.clim.ui.splash

import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Logics on 12/01/2018.
 */
class SplashPresenter<V : SplashMvpView> @Inject constructor(var dataManager: DataManager) : BasePresenter<V>(), SplashMvpPresenter<V> {

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)
        decideNextActivity()
    }

    private fun decideNextActivity() {
        if (dataManager.getCurrentLoginMode() == DataManager.LoginMode.LOGGED_IN_MODE_LOGGED_OUT.mType) {
            (getMvpView() as SplashMvpView).openLoginActivity()
        } else {
            (getMvpView() as SplashMvpView).openMainActivity()
        }
    }

}