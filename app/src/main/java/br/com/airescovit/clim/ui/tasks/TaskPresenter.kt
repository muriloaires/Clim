package br.com.airescovit.clim.ui.tasks

import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.data.db.model.Task
import br.com.airescovit.clim.ui.base.BasePresenter
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by murilo aires on 28/01/2018.
 */
class TaskPresenter<V : TasksMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), TaskMvpPresenter<V> {

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
        getMvpView()?.openWhatsApp((getTasks()[position] as Task).client.phone  )
    }
}