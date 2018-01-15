package br.com.airescovit.clim.ui.base

import android.support.annotation.StringRes

/**
 * Created by Logics on 10/01/2018.
 */
interface MvpView {

    fun showLoading()

    fun hideLoading()

    fun openActivityOnTokenExpire()

    fun onError(@StringRes resId: Int)

    fun onError(error: String?)

    fun showMessage(message: String?)

    fun showMessage(@StringRes messageResId: Int)

    fun isNetworkConnected(): Boolean

    fun hideKeyboard()
}