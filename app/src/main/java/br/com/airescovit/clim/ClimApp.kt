package br.com.airescovit.clim

import android.app.Application
import br.com.airescovit.clim.di.component.ApplicationComponent
import br.com.airescovit.clim.di.component.DaggerApplicationComponent
import br.com.airescovit.clim.di.module.ApplicationModule

/**
 * Created by Logics on 12/01/2018.
 */
class ClimApp : Application() {
    lateinit var mApplicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        mApplicationComponent.inject(this)
    }
}