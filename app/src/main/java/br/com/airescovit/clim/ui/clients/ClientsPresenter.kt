package br.com.airescovit.clim.ui.clients

import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by murilo aires on 27/01/2018.
 */
class ClientsPresenter<V : ClientsMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), ClientsMvpPresenter<V> {
    override fun onFabClick() {
        getMvpView()?.openAddClientsActivity()
    }
}