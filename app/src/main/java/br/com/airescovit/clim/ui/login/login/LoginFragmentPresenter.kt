package br.com.airescovit.clim.ui.login.login

import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Logics on 15/01/2018.
 */
class LoginFragmentPresenter<V : LoginFragmentMvpView> @Inject constructor() : BasePresenter<V>(), LoginFragmentMvpPresenter<V> {
    override fun onButtonCriarContaClick() {
        getMvpView()!!.showRegisterFragment()
    }

    override fun onButtonServerClick(email: String, password: String) {
        if (email == "") {
            getMvpView()!!.onIncorrectEmail(R.string.campo_obrigatorio)
            return
        }

        if (password == "") {
            getMvpView()!!.onIncorrectPassword(R.string.campo_obrigatorio)
            return
        }
    }
}