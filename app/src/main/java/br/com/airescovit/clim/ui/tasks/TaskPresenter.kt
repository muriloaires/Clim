package br.com.airescovit.clim.ui.tasks

import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by murilo aires on 28/01/2018.
 */
class TaskPresenter<V : TasksMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), TaskMvpPresenter<V> {
}