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
    fun onBtnCallClick(position: Int)
    fun onBtnWhatsappClick(position: Int)
    fun getClientName(position: Int): String
    fun getClientStateCity(position: Int): String
    fun getClientStreetAddress(position: Int): String
    fun getTaskServiceFee(position: Int): String
    fun getTaskTitle(position: Int): String
    fun getTaskDate(position: Int): String
}