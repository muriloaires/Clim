package br.com.airescovit.clim.ui.clients

import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.ui.base.MvpView

/**
 * Created by murilo aires on 27/01/2018.
 */
interface ClientsMvpView : MvpView {
    fun openAddClientsActivity()
    fun updateClientsList(clients: List<Client>)
}