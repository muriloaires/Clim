package br.com.airescovit.clim.ui.tasks


import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.PhoneLookup
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.addtask.AddTaskActivity
import br.com.airescovit.clim.ui.base.BaseFragment
import br.com.airescovit.clim.ui.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_tasks.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class TasksFragment : BaseFragment(), TasksMvpView {

    companion object {
        fun getInstance(): TasksFragment {
            return TasksFragment()
        }

        const val REQUEST_ADD_TASK: Int = 2
    }


    private lateinit var mTasksAdapter: TaskAdapter
    private lateinit var mScrollListener: EndlessScrollListener
    @Inject
    lateinit var mPresenter: TaskMvpPresenter<TasksMvpView>
    lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val component = getActivityComponent()
        if (component != null) {
            component.inject(this)
            mPresenter.onAttach(this)
        }
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun setUp(view: View) {
        noTaskLayout.setOnClickListener({ mPresenter.onFabClick() })
        mLinearLayoutManager = LinearLayoutManager(context)
        recyclerTasks.layoutManager = mLinearLayoutManager
        mTasksAdapter = TaskAdapter(mPresenter)
        recyclerTasks.adapter = mTasksAdapter

        mScrollListener = object : EndlessScrollListener(mLinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                mPresenter.onRecylerLoadMore()
            }
        }
        mPresenter.onViewReady()
    }


    override fun openAddTasksActivity() {
        startActivityForResult(Intent(activity, AddTaskActivity::class.java), REQUEST_ADD_TASK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_ADD_TASK -> {
                if (resultCode == Activity.RESULT_OK) {
                    mPresenter.onAddTaskActivityReturn()
                }
            }
            11 -> {
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        var phoneNo: String? = null
                        var name: String? = null
                        var cursor: Cursor?
                        val uri = data?.getData()
                        cursor = activity?.contentResolver?.query(uri, null, null, null, null)
                        cursor?.moveToFirst()
                        val phoneIndex = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        val nameIndex = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                        phoneNo = cursor?.getString(phoneIndex!!)
                        name = cursor?.getString(nameIndex!!)

                        Log.e("Name and Contact number", name + "," + phoneNo)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }

    override fun updateTasksList() {
        mTasksAdapter.notifyDataSetChanged()
    }

    override fun resetScrollListener() {
        mScrollListener.resetState()
    }

    override fun removeScrollListener() {
        recyclerTasks.removeOnScrollListener(mScrollListener)
    }

    override fun setOnScrollListener() {
        recyclerTasks.addOnScrollListener(mScrollListener)
    }

    override fun showNoTasksView() {
        noTaskLayout.visibility = View.VISIBLE
        recyclerTasks.visibility = View.GONE
    }

    override fun hideNoTaskView() {
        noTaskLayout.visibility = View.GONE
        recyclerTasks.visibility = View.VISIBLE
    }

    override fun openCallActivity(phoneData: Uri) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = phoneData
        startActivity(intent)
    }

    override fun openWhatsApp(phone: String) {
        val contactId = contactIdByPhoneNumber("981036616")

        val contactCursor = activity?.contentResolver?.query(
                ContactsContract.RawContacts.CONTENT_URI,
                arrayOf(ContactsContract.RawContacts.CONTACT_ID),
                ContactsContract.RawContacts.ACCOUNT_TYPE + "= ? AND " + ContactsContract.RawContacts._ID + "= ?",
                arrayOf("com.whatsapp", contactId),
                null)

        if (contactCursor!!.moveToFirst()) {
            val whatsappContactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID))
        }

        val resolver = context?.contentResolver
        val cursor = resolver?.query(
                ContactsContract.Data.CONTENT_URI, null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME)

//Now read data from cursor like

        while (cursor!!.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID))
            val displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME))
            val mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE))

            Log.d("Data", id.toString() + " " + displayName + " " + mimeType)

        }

        val intent = Intent()
        intent.action = Intent.ACTION_VIEW

        intent.setDataAndType(Uri.parse("content://com.android.contacts/data/19809"),
                "vnd.android.cursor.item/vnd.com.whatsapp.profile")
        intent.`package` = "com.whatsapp"

        startActivity(intent)

    }

    fun contactIdByPhoneNumber(phoneNumber: String?): String? {
        var contactId: String? = null
        if (phoneNumber != null && phoneNumber.isNotEmpty()) {
            val contentResolver = activity?.contentResolver

            val uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber))

            val projection = arrayOf(ContactsContract.PhoneLookup.CONTACT_ID)

            val cursor = contentResolver?.query(uri, projection, null, null, null)

            if (cursor != null) {
                while (cursor!!.moveToNext()) {
                    contactId = cursor!!.getString(cursor!!.getColumnIndexOrThrow(PhoneLookup._ID))
                }
                cursor!!.close()
            }
        }
        return contactId
    }

    override fun showWhatsAppProfileActivity(data: Uri, type: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(data, type)
        intent.`package` = "com.whatsapp"
        startActivity(intent)
    }
}// Required empty public constructor
