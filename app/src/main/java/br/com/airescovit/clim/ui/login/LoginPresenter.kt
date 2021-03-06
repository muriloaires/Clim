package br.com.airescovit.clim.ui.login

import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Logics on 15/01/2018.
 */
class LoginPresenter<V : LoginMvpView> @Inject constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), LoginMvpPresenter<V> {
    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)
        getMvpView()!!.showLoginFragment()
    }
}