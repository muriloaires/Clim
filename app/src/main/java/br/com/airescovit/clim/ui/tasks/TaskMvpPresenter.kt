package br.com.airescovit.clim.ui.tasks

import br.com.airescovit.clim.data.db.model.Task
import br.com.airescovit.clim.ui.base.MvpPresenter

/**
 * Created by murilo aires on 28/01/2018.
 */
interface TaskMvpPresenter<V : TasksMvpView> : MvpPresenter<V>{
    fun onFabClick()
    fun onAddTaskActivityReturn()
    fun getTasks(): List<Task?>
    fun onViewReady()
    fun onRecylerLoadMore()
}