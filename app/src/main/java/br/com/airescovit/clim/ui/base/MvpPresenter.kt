package br.com.airescovit.clim.ui.base

/**
 * Created by Logics on 12/01/2018.
 */
interface MvpPresenter<in V : MvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleApiError(error: String)

    fun setUserAsLoggedOut()
}