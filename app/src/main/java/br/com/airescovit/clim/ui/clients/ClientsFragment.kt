package br.com.airescovit.clim.ui.clients


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.airescovit.clim.R
import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.ui.addclients.AddClientsActivity
import br.com.airescovit.clim.ui.base.BaseFragment
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

        val REQUEST_ADD_CLIENT: Int = 1
    }

    @Inject lateinit var mPresenter: ClientsMvpPresenter<ClientsMvpView>
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
        fabAddAclients.setOnClickListener({ mPresenter.onFabClick() })
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

    override fun updateClientsList(clients: List<Client>) {

    }
}// Required empty public constructor
