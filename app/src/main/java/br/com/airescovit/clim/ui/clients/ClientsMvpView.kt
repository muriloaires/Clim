package br.com.airescovit.clim.ui.clients

import android.content.Intent
import br.com.airescovit.clim.ui.base.MvpView

/**
 * Created by murilo aires on 27/01/2018.
 */
interface ClientsMvpView : MvpView {

    fun openAddClientsActivity()

    fun updateClientsList()

    fun resetScrollListener()

    fun removeScrollListener()

    fun setOnScrollListener()

    fun showNoClientsView()

    fun hideNoClientView()

    fun openAddTasksActivity(intent: Intent)

    fun finishWithOkResult(data: Intent)

    fun showTaskFragment()

    fun showDialogNewOrExistent()

    fun openContactPickerActivity()

}