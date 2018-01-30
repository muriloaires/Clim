package br.com.airescovit.clim.ui.add_clients

import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by murilo aires on 29/01/2018.
 */
class AddClientsPresenter<V : AddClientsMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), AddClientsMvpPresenter<V>