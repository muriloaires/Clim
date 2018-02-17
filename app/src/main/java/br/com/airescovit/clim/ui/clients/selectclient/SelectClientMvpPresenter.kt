package br.com.airescovit.clim.ui.clients.selectclient

import br.com.airescovit.clim.ui.base.MvpPresenter

/**
 * Created by murilo aires on 12/02/2018.
 */
interface SelectClientMvpPresenter<V : SelectClientMvpView> : MvpPresenter<V> {
    fun onFabClick()
    fun onAddClientsActivityReturn()
    fun onViewReady()
    fun onRecylerLoadmore()
}