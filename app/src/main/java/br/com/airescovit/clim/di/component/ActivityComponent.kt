package br.com.airescovit.clim.di.component

import br.com.airescovit.clim.di.PerActivity
import br.com.airescovit.clim.di.module.ActivityModule
import br.com.airescovit.clim.ui.login.LoginActivity
import br.com.airescovit.clim.ui.splash.Splash
import dagger.Component

/**
 * Created by Logics on 12/01/2018.
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent : ApplicationComponent{
    fun inject(splash: Splash)
    fun inject(loginActivity: LoginActivity)
}