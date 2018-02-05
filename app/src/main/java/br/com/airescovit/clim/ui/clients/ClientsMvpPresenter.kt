package br.com.airescovit.clim.ui.clients

import br.com.airescovit.clim.ui.base.MvpPresenter

/**
 * Created by murilo aires on 27/01/2018.
 */
interface ClientsMvpPresenter<V : ClientsMvpView> : MvpPresenter<V> {
    fun onFabClick()
    fun onAddClientsActivityReturn()
}