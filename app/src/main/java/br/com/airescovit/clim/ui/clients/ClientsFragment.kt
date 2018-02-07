package br.com.airescovit.clim.ui.clients


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.addclients.AddClientsActivity
import br.com.airescovit.clim.ui.base.BaseFragment
import br.com.airescovit.clim.ui.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_clients.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ClientsFragment : BaseFragment(), ClientsMvpView {

    companion object {
        fun getInstance(): ClientsFragment {
            return ClientsFragment()
        }

        const val REQUEST_ADD_CLIENT: Int = 1
    }

    private lateinit var clientsAdapter: ClientsAdapter
    private lateinit var mScrollListener: EndlessScrollListener
    @Inject lateinit var mPresenter: ClientsMvpPresenter<ClientsMvpView>
    lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val component = getActivityComponent()
        if (component != null) {
            component.inject(this)
            mPresenter.onAttach(this)
        }
        return inflater.inflate(R.layout.fragment_clients, container, false)
    }

    override fun setUp(view: View) {
        setHasOptionsMenu(true)
        noClientsLayout.setOnClickListener({ mPresenter.onFabClick() })
        mLinearLayoutManager = LinearLayoutManager(context)
        recyclerClients.layoutManager = mLinearLayoutManager
        clientsAdapter = ClientsAdapter(mPresenter)
        recyclerClients.adapter = clientsAdapter

        mScrollListener = object : EndlessScrollListener(mLinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                mPresenter.onRecylerLoadmore()
            }
        }
        mPresenter.onViewReady()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_clients, menu)
    }

    override fun openAddClientsActivity() {
        startActivityForResult(Intent(activity, AddClientsActivity::class.java), REQUEST_ADD_CLIENT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_ADD_CLIENT -> {
                if (resultCode == Activity.RESULT_OK) {
                    mPresenter.onAddClientsActivityReturn()
                }
            }
        }
    }

    override fun updateClientsList() {
        clientsAdapter.notifyDataSetChanged()
    }

    override fun resetScrollListener() {
        mScrollListener.resetState()
    }

    override fun removeScrollListener() {
        recyclerClients.removeOnScrollListener(mScrollListener)
    }

    override fun setOnScrollListener() {
        recyclerClients.addOnScrollListener(mScrollListener)
    }

    override fun showNoClientsView() {
        noClientsLayout.visibility = View.VISIBLE
        recyclerClients.visibility = View.GONE
    }

    override fun hideNoClientView() {
        noClientsLayout.visibility = View.GONE
        recyclerClients.visibility = View.VISIBLE
    }
}
