package br.com.airescovit.clim.ui.addtask

import android.content.Intent
import br.com.airescovit.clim.ui.base.MvpPresenter

/**
 * Created by Murilo Aires on 09/02/2018.
 */
interface AddTaskMvpPresenter<V : AddTaskMvpView> : MvpPresenter<V> {

    fun onSelectClientClick()

    fun onClientSelected(data: Intent?)

    fun onDatePicked(year: Int, monthOfYear: Int, dayOfMonth: Int)

    fun onCheckClick(title: String, description: String, valor: String)

    fun handleIntent(intent: Intent?)

}