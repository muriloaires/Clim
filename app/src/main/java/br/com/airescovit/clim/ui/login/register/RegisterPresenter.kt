package br.com.airescovit.clim.ui.login.register

import br.com.airescovit.clim.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Logics on 16/01/2018.
 */
class RegisterPresenter<V : RegisterMvpView> @Inject constructor() : BasePresenter<V>(), RegisterMvpPresenter<V>