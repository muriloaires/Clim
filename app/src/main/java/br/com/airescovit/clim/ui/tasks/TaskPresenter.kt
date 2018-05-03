package br.com.airescovit.clim.ui.tasks

import android.net.Uri
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.data.db.model.Task
import br.com.airescovit.clim.ui.base.BasePresenter
import br.com.airescovit.clim.utils.AppConstants
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by murilo aires on 28/01/2018.
 */
class TaskPresenter<V : TasksMvpView> @Inject constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, compositeDisposable), TaskMvpPresenter<V> {

    private var tasks: MutableList<Any?> = mutableListOf()
    private var page = 1

    init {
        tasks.add(0, Any())
        tasks.add(1, null)
    }

    override fun onAddTaskActivityReturn() {
        loadAllTaskssFromDb()
    }

    private fun loadAllTaskssFromDb() {
        dataManager.loadAllTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it: List<Task> ->
                    if (!it.isEmpty()) {
                        getMvpView()?.hideNoTaskView()
                    }
                    this.tasks.addAll(this.tasks.size - 1, it)
                    getMvpView()?.updateTasksList()
                    loadFromAPI(page)
                })
    }

    override fun onFabClick() {
        getMvpView()?.openAddTasksActivity()
    }

    override fun getTasks(): List<Any?> {
        return tasks
    }

    override fun onViewReady() {
        loadAllTaskssFromDb()
    }

    private fun loadFromAPI(page: Int) {
        getMvpView()?.resetScrollListener()
        dataManager.getTasksAPI(dataManager.getCurrentAccessToken(), dataManager.getCurrentUserId(), page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tasks ->
                    for (task: Task in tasks) {
                        task.attachEntities()
                    }
                    dataManager.insertTaskList(tasks)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()

                    if (this.page == 1) {
                        getMvpView()?.setOnScrollListener()
                        this.tasks.clear()
                        this.tasks.add(Any())
                        this.tasks.add(null)


                        if (tasks.isEmpty()) {
                            getMvpView()?.showNoTasksView()
                        }
                    }

                    if (tasks.size < 20) {
                        this.tasks.remove(null)
                        this.tasks.addAll(this.tasks.size, tasks)
                        getMvpView()?.removeScrollListener()
                    } else {
                        this.tasks.addAll(this.tasks.size - 1, tasks)
                        this.page++
                    }

                    getMvpView()?.updateTasksList()
                }, { err ->
                    handleApiError(err as HttpException)
                })
    }

    override fun onRecylerLoadMore() {
        page++
        loadFromAPI(page)
    }

    override fun onBtnReagendarClick(position: Int) {
        getMvpView()?.openWhatsApp((getTasks()[position] as Task).client.phone)
    }

    override fun onBtnCallClick(position: Int) {
        val phone = (tasks[position] as Task).client.phone
        getMvpView()?.openCallActivity(Uri.parse("tel:$phone"))
    }

    override fun onBtnWhatsappClick(position: Int) {
        val task = tasks[position] as Task
        mCompositeDisposable.add(dataManager.getWhatsAppProfileId(task.client.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isEmpty()) {
                    } else {
                        getMvpView()?.showWhatsAppProfileActivity(Uri.parse("content://com.android.contacts/data/$it"), "vnd.android.cursor.item/vnd.com.whatsapp.profile")
                    }
                })
    }

    override fun getClientName(position: Int): String {
        return (tasks[position] as Task).client.name
    }

    override fun getClientStateCity(position: Int): String {
        val task = tasks[position] as Task
        return try {
            task.client.address.state + " - " + task.client.address.city
        } catch (e: Exception) {
            " - "
        }
    }

    override fun getClientStreetAddress(position: Int): String {
        val task = tasks[position] as Task
        return try {
            task.client.address.street + " - " + task.client.address.neighborhood
        } catch (e: Exception) {
            " - "
        }
    }

    override fun getTaskServiceFee(position: Int): String {
        return NumberFormat.getCurrencyInstance().format((tasks[position] as Task).serviceFee)
    }

    override fun getTaskTitle(position: Int): String {
        return (tasks[position] as Task).title
    }

    override fun getTaskDate(position: Int): String {
        return SimpleDateFormat(AppConstants.DATE_FORMAT_EXIBITION, Locale.getDefault()).format((tasks[position] as Task).startAt)
    }
}