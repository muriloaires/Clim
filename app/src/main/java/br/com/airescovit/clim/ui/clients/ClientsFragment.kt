package br.com.airescovit.clim.ui.clients


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseFragment
import br.com.airescovit.clim.ui.clients.addclients.AddClientsActivity
import br.com.airescovit.clim.ui.main.MainActivity
import br.com.airescovit.clim.ui.tasks.TasksFragment
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
        const val RESULT_PICK_CONTACT: Int = 3
    }


    @Inject
    lateinit var mPresenter: ClientsMvpPresenter<ClientsMvpView>
    private lateinit var clientsAdapter: ClientsAdapter
    private lateinit var mScrollListener: EndlessScrollListener
    private lateinit var mLinearLayoutManager: LinearLayoutManager

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
        clientsAdapter = ClientsAdapter(mPresenter as ClientsListAction)
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
            TasksFragment.REQUEST_ADD_TASK -> {
                if (resultCode == Activity.RESULT_OK) {
                    mPresenter.onAddTaskActivityReturn()
                }
            }
            RESULT_PICK_CONTACT -> {
                if (resultCode == Activity.RESULT_OK) {
                    mPresenter.onContactPicked(data!!.getData())
                }
//                val uri = data!!.getData()
//                //Query the content uri
//                val cursor = activity?.contentResolver?.query(uri, null, null, null, null)
//                cursor?.moveToFirst()
//                // column index of the phone number
//                val idIndex = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
//                // column index of the contact name
//                val contactId = cursor?.getInt(idIndex!!)
//                cursor?.close()
//
//                val contactCursor = activity?.contentResolver?.query(
//                        ContactsContract.RawContacts.CONTENT_URI,
//                        arrayOf(ContactsContract.RawContacts._ID), ContactsContract.RawContacts.CONTACT_ID + "= ?" + "AND " + ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",
//                        arrayOf(contactId.toString(), "com.whatsapp"),
//                        null)
//
//                var rawContactId: String? = null
//                if (contactCursor!!.moveToFirst()) {
//                    rawContactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.RawContacts._ID))
//                    Log.d("ACC_TYPE", rawContactId)
//                }
//                contactCursor.close()
//
//                val contactCursor2 = activity?.contentResolver?.query(
//                        ContactsContract.Data.CONTENT_URI,
//                        arrayOf(ContactsContract.Data._ID), ContactsContract.Data.RAW_CONTACT_ID + "= ?" + "AND " + ContactsContract.Data.MIMETYPE + "= ?",
//                        arrayOf(rawContactId, "vnd.android.cursor.item/vnd.com.whatsapp.profile"),
//                        null)
//
//                var whatsappContactId: String? = null
//                if (contactCursor2!!.moveToFirst()) {
//                    whatsappContactId = contactCursor2.getString(contactCursor2.getColumnIndex(ContactsContract.Data._ID))
//                }
//                contactCursor2.close()
//
//                val intent = Intent()
//                intent.action = Intent.ACTION_VIEW
//
//                intent.setDataAndType(Uri.parse("content://com.android.contacts/data/$whatsappContactId"),
//                        "vnd.android.cursor.item/vnd.com.whatsapp.profile")
//                intent.`package` = "com.whatsapp"
//
//                startActivity(intent)
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

    override fun openAddTasksActivity(intent: Intent) {
        startActivityForResult(intent, TasksFragment.REQUEST_ADD_TASK)
    }

    override fun finishWithOkResult(data: Intent) {
        //Does nothing
    }

    override fun showTaskFragment() {
        (getBaseActivity() as MainActivity).selectTaskTab()
    }

    override fun showDialogNewOrExistent() {
        val builder = AlertDialog.Builder(activity)
                .setMessage(R.string.new_or_from_contacts)
                .setPositiveButton(R.string.contacts, { _, _ ->
                    mPresenter.onAddFromContactsSelected()
                })
                .setNegativeButton(R.string.newclient, { _, _ ->
                    mPresenter.onNewClientSelected()
                })
        builder.show()
    }

    override fun openContactPickerActivity() {
        val contactPickerIntent = Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT)
    }
}
