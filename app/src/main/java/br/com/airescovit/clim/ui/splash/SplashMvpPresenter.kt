package br.com.airescovit.clim.ui.splash

import br.com.airescovit.clim.di.PerActivity
import br.com.airescovit.clim.ui.base.MvpPresenter

/**
 * Created by Logics on 12/01/2018.
 */
@PerActivity
interface SplashMvpPresenter<V : SplashMvpView> : MvpPresenter<V>