package br.com.airescovit.clim.ui.login.login

import br.com.airescovit.clim.ui.base.MvpPresenter
import br.com.airescovit.clim.ui.login.LoginMvpView

/**
 * Created by Logics on 15/01/2018.
 */
interface LoginFragmentMvpPresenter<V : LoginFragmentMvpView> : MvpPresenter<V> {

    fun onButtonServerClick(email: String, password: String)
    fun onButtonCriarContaClick()
}