package br.com.airescovit.clim.ui.login.register

import br.com.airescovit.clim.ui.base.MvpPresenter

/**
 * Created by Logics on 16/01/2018.
 */
interface RegisterMvpPresenter<V : RegisterMvpView> : MvpPresenter<V> {

    fun onBtnRegisterClick(firstName: String, lastName: String, email: String, password: String, confirmPassword: String)
}