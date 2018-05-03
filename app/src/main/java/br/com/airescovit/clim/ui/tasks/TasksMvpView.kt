package br.com.airescovit.clim.ui.tasks

import android.net.Uri
import br.com.airescovit.clim.ui.base.MvpView

/**
 * Created by murilo aires on 28/01/2018.
 */
interface TasksMvpView : MvpView {

    fun openAddTasksActivity()
    fun updateTasksList()
    fun resetScrollListener()
    fun removeScrollListener()
    fun setOnScrollListener()
    fun showNoTasksView()
    fun hideNoTaskView()
    fun openWhatsApp(phone: String)
    fun openCallActivity(phoneData: Uri)
    fun showWhatsAppProfileActivity(data: Uri, type: String)
}