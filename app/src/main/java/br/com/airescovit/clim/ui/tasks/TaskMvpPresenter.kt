package br.com.airescovit.clim.ui.tasks

import br.com.airescovit.clim.ui.base.MvpPresenter

/**
 * Created by murilo aires on 28/01/2018.
 */
interface TaskMvpPresenter<V : TasksMvpView> : MvpPresenter<V> {
    fun onFabClick()
    fun onAddTaskActivityReturn()
    fun getTasks(): List<Any?>
    fun onViewReady()
    fun onRecylerLoadMore()
    fun onBtnReagendarClick(position: Int)
}