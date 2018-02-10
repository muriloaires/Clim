package br.com.airescovit.clim.ui.clients

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.airescovit.clim.data.db.model.Client
import kotlinx.android.synthetic.main.client_item.view.*

/**
 * Created by Logics on 05/02/2018.
 */
class ClientViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    val textClientName = view.textClientName
    val textStateCity = view.textStateCity
    val textStreetNeighborhood = view.textStreetNeighborhood
    val textPostalCode = view.textPostalCode
    val imgAgendarVisita = view.imgAgendarVisita

}