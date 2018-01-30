package br.com.airescovit.clim.ui.main

import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by murilo aires on 27/01/2018.
 */
class MainPresenter<V : MainMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), MainMvpPresenter<V>