package br.com.airescovit.clim.ui.splash

import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Logics on 12/01/2018.
 */
class SplashPresenter<in V : SplashMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), SplashMvpPresenter<V> {

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)
        decideNextActivity()
    }

    private fun decideNextActivity() {
        if (mDataManager.getCurrentLoginMode() == DataManager.LoginMode.LOGGED_IN_MODE_LOGGED_OUT.mType) {
            (getMvpView() as SplashMvpView).openLoginActivity()
        } else {
            (getMvpView() as SplashMvpView).openMainActivity()
        }
    }

}