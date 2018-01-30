package br.com.airescovit.clim.ui.login.login

import android.support.annotation.StringRes
import br.com.airescovit.clim.ui.base.MvpView

/**
 * Created by Logics on 15/01/2018.
 */
interface LoginFragmentMvpView : MvpView {
    fun onIncorrectEmail(@StringRes resId: Int)
    fun onIncorrectPassword(@StringRes resId: Int)
    fun showRegisterFragment()
    fun startMainActivity()
}