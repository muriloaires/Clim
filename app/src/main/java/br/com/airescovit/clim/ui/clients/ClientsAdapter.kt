package br.com.airescovit.clim.ui.clients

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.airescovit.clim.R
import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.ui.base.LoadViewHolder

/**
 * Created by Murilo Aires on 05/02/2018.
 */
class ClientsAdapter(val mClientsListAction: ClientsListAction) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val CLIENT: Int = 0
    private val LOADING: Int = 1
    private val ADD: Int = 2

    override fun getItemCount(): Int = mClientsListAction.getList().size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is AddClientViewHolder) {
            holder.rootView.setOnClickListener {
                mClientsListAction.onAddClientClick()
            }
        } else if (holder is ClientViewHolder) {
            holder.textClientName.text = mClientsListAction.getClientName(position)
            holder.textStateCity.text = mClientsListAction.getClientStateCity(position)//client?.address?.state + " - " + client?.address?.city
            holder.textStreetNeighborhood.text = mClientsListAction.getClientStreetNeighborhood(position)//client?.address?.street + " - " + client?.address?.neighborhood
            holder.textPostalCode.text = mClientsListAction.getClientZipCode(position)//client?.address?.postalCode
            holder.imgAgendarVisita.setOnClickListener {
                mClientsListAction.onAddTaskClick(position)
            }
            if (mClientsListAction.isSelection()) {
                holder.imgAgendarVisita.visibility = View.GONE
            } else {
                holder.imgAgendarVisita.visibility = View.VISIBLE
            }

            holder.imgAgendarVisita.setOnClickListener({ mClientsListAction.onAddTaskClick(position) })
            holder.root.setOnClickListener({ mClientsListAction.onItemClick(position) })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == CLIENT) {
            val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.client_item, parent, false)
            return ClientViewHolder(view)
        } else if (viewType == LOADING) {
            val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.load_item, parent, false)
            return LoadViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.add_client_item, parent, false)
            return AddClientViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (mClientsListAction.getList()[position] is Client) {
            return CLIENT
        } else if (mClientsListAction.getList()[position] is Any) {
            return ADD
        } else {
            return LOADING
        }
    }
}