package br.com.airescovit.clim.ui.login.register

import br.com.airescovit.clim.ui.base.MvpView

/**
 * Created by Logics on 16/01/2018.
 */
interface RegisterMvpView : MvpView {
    fun onIncorrectName(resId: Int)
    fun onIncorrectEmail(resId: Int)
    fun onIncorrectPassword(resId: Int)
    fun startMainActivity()
    fun onDifferentsPasswords(resId: Int)
}