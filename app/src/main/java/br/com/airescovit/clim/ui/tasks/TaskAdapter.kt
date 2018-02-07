package br.com.airescovit.clim.ui.tasks

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.LoadViewHolder
import br.com.airescovit.clim.ui.clients.AddClientViewHolder
import br.com.airescovit.clim.ui.clients.ClientViewHolder

/**
 * Created by Murilo Aires on 07/02/2018.
 */
class TaskAdapter(val mPresenter: TaskMvpPresenter<TasksMvpView>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TASK: Int = 0
    private val LOADING: Int = 1
    private val ADD: Int = 2

    override fun getItemCount(): Int = /*mPresenter.getTasks().size +*/ 10


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
//        if (position == 0) {
////            (holder as AddTaskViewHolder).rootView.setOnClickListener {
////                mPresenter.onFabClick()
////            }
//        } else if (position != 0 && mPresenter.getTasks()[position - 1] != null) {
//
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TASK -> {
                val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.task_item, parent, false)
                TaskViewHolder(view)
            }
            LOADING -> {
                val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.load_item, parent, false)
                LoadViewHolder(view)
            }
            else -> {
                val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.add_task_item, parent, false)
                AddTaskViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> ADD
//            mPresenter.getTasks()[position - 1] == null -> LOADING
            else -> TASK
        }
    }
}