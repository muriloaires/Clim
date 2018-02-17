package br.com.airescovit.clim.ui.clients.selectclient

import android.content.Intent
import br.com.airescovit.clim.ui.base.MvpView

/**
 * Created by murilo aires on 12/02/2018.
 */
interface SelectClientMvpView : MvpView {

    fun openAddClientsActivity()

    fun updateClientsList()

    fun resetScrollListener()

    fun removeScrollListener()

    fun setOnScrollListener()

    fun showNoClientsView()

    fun hideNoClientView()

    fun openAddTasksActivity(intent: Intent)

    fun finishWithOkResult(data: Intent)
}