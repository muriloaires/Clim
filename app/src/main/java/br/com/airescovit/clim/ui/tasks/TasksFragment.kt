package br.com.airescovit.clim.ui.tasks


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*

import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.addclients.AddClientsActivity
import br.com.airescovit.clim.ui.addtask.AddTaskActivity
import br.com.airescovit.clim.ui.base.BaseFragment
import br.com.airescovit.clim.ui.clients.ClientsAdapter
import br.com.airescovit.clim.ui.clients.ClientsFragment
import br.com.airescovit.clim.ui.clients.ClientsFragment.Companion.REQUEST_ADD_CLIENT
import br.com.airescovit.clim.ui.clients.ClientsMvpPresenter
import br.com.airescovit.clim.ui.clients.ClientsMvpView
import br.com.airescovit.clim.ui.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_clients.*
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

        const val REQUEST_ADD_TASK: Int = 1
    }


    private lateinit var mTasksAdapter: TaskAdapter
    private lateinit var mScrollListener: EndlessScrollListener
    @Inject lateinit var mPresenter: TaskMvpPresenter<TasksMvpView>
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

}// Required empty public constructor
