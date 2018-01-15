package br.com.airescovit.clim.di.component

import android.app.Application
import android.content.Context
import br.com.airescovit.clim.ClimApp
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.di.ApplicationContext
import br.com.airescovit.clim.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(app: ClimApp)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun getDataManager(): DataManager
}