package br.com.airescovit.clim.ui.login

import br.com.airescovit.clim.ui.base.MvpView

/**
 * Created by Logics on 15/01/2018.
 */
interface LoginMvpView : MvpView {

    fun showLoginFragment()

    fun showRegisterFragment()

    fun openMainActivity()
}