package br.com.airescovit.clim.ui.addtask

import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Murilo Aires on 09/02/2018.
 */
class AddTaskPresenter<V : AddTaskMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), AddTaskMvpPresenter<V> {
}