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
class ClientsAdapter(val mPresenter: ClientsMvpPresenter<ClientsMvpView>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val CLIENT: Int = 0
    private val LOADING: Int = 1
    private val ADD: Int = 2

    override fun getItemCount(): Int = mPresenter.getClients().size + 1


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (position == 0) {
            (holder as AddClientViewHolder).rootView.setOnClickListener {
                mPresenter.onFabClick()
            }
        } else if (position != 0 && mPresenter.getClients()[position - 1] != null) {
            val client = mPresenter.getClients()[position - 1]
            (holder as ClientViewHolder).textClientName.text = client?.name
            holder.textStateCity.text = client?.address?.state + " - " + client?.address?.city
            holder.textStreetNeighborhood.text = client?.address?.street + " - " + client?.address?.neighborhood
            holder.textPostalCode.text = client?.address?.postalCode
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
            position == 0 -> ADD
            mPresenter.getClients()[position - 1] == null -> LOADING
            else -> CLIENT
        }
    }
}