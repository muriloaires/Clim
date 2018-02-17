package br.com.airescovit.clim.ui.clients.selectclient

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import br.com.airescovit.clim.ui.clients.ClientsAdapter
import br.com.airescovit.clim.ui.clients.ClientsFragment
import br.com.airescovit.clim.ui.clients.ClientsListAction
import br.com.airescovit.clim.ui.clients.addclients.AddClientsActivity
import br.com.airescovit.clim.ui.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_clients.*
import javax.inject.Inject

class SelectClientActivity : BaseActivity(), SelectClientMvpView {

    @Inject
    lateinit var mPresenter: SelectClientMvpPresenter<SelectClientMvpView>
    private lateinit var clientsAdapter: ClientsAdapter
    private lateinit var mScrollListener: EndlessScrollListener
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_client)

        getActivityComponent().inject(this)
        mPresenter.onAttach(this)

        noClientsLayout.setOnClickListener({ mPresenter.onFabClick() })
        mLinearLayoutManager = LinearLayoutManager(this)
        recyclerClients.layoutManager = mLinearLayoutManager
        clientsAdapter = ClientsAdapter(mPresenter as ClientsListAction)
        recyclerClients.adapter = clientsAdapter

        mScrollListener = object : EndlessScrollListener(mLinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                mPresenter.onRecylerLoadmore()
            }
        }
        mPresenter.onViewReady()
    }

    override fun openAddClientsActivity() {
        startActivityForResult(Intent(this, AddClientsActivity::class.java), ClientsFragment.REQUEST_ADD_CLIENT)
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

    override fun openAddTasksActivity(intent: Intent) {
        //Does nothing
    }

    override fun finishWithOkResult(data: Intent) {
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
