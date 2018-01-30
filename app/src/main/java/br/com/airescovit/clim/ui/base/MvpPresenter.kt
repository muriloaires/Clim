package br.com.airescovit.clim.ui.base

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException

/**
 * Created by Logics on 12/01/2018.
 */
interface MvpPresenter<in V : MvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleApiError(error: HttpException)

    fun setUserAsLoggedOut()
}