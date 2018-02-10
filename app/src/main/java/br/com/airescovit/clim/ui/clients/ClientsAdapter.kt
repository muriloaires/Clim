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
            val client = mClientsListAction.getList()[position] as Client
            holder.textClientName.text = client?.name
            holder.textStateCity.text = client?.address?.state + " - " + client?.address?.city
            holder.textStreetNeighborhood.text = client?.address?.street + " - " + client?.address?.neighborhood
            holder.textPostalCode.text = client?.address?.postalCode
            holder.imgAgendarVisita.setOnClickListener {
                mClientsListAction.onAddTaskClick(position)
            }
            if (mClientsListAction.isSelection()) {
                holder.imgAgendarVisita.visibility = View.GONE
            } else {
                holder.imgAgendarVisita.visibility = View.VISIBLE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CLIENT -> {
                val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.client_item, parent, false)
                ClientViewHolder(view)
            }
            LOADING -> {
                val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.load_item, parent, false)
                LoadViewHolder(view)
            }
            else -> {
                val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.add_client_item, parent, false)
                AddClientViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            mClientsListAction.getList()[position] is Any -> ADD
            mClientsListAction.getList()[position] is Client -> CLIENT
            else -> LOADING
        }
    }
}