package br.com.airescovit.clim.ui.tasks

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.airescovit.clim.R
import br.com.airescovit.clim.data.db.model.Task
import br.com.airescovit.clim.ui.base.LoadViewHolder


/**
 * Created by Murilo Aires on 07/02/2018.
 */
class TaskAdapter(val mPresenter: TaskMvpPresenter<TasksMvpView>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TASK: Int = 0
    private val LOADING: Int = 1
    private val ADD: Int = 2

    override fun getItemCount(): Int = mPresenter.getTasks().size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is AddTaskViewHolder) {
            holder.rootView.setOnClickListener {
                mPresenter.onFabClick()
            }
        } else if (holder is TaskViewHolder) {
            holder.textClientName.text = mPresenter.getClientName(position)
            holder.textStateCity.text = mPresenter.getClientStateCity(position)
            holder.textStreetNeighborhood.text = mPresenter.getClientStreetAddress(position)
            holder.textServiceFee.text = mPresenter.getTaskServiceFee(position)
            holder.textTaskTitle.text = mPresenter.getTaskTitle(position)
            holder.textDia.text = mPresenter.getTaskDate(position)
            holder.btnReagendar.setOnClickListener { mPresenter.onBtnReagendarClick(position) }
            holder.btnCall.setOnClickListener { mPresenter.onBtnCallClick(position) }
            holder.btnWhatsapp.setOnClickListener { mPresenter.onBtnWhatsappClick(position) }
        }
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
            mPresenter.getTasks()[position] is Task -> TASK
            mPresenter.getTasks()[position] is Any -> ADD
            else -> LOADING
        }
    }


}