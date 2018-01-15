package br.com.airescovit.clim.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import br.com.airescovit.clim.data.AppDataManager
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.di.ActivityContext
import br.com.airescovit.clim.di.PerActivity
import br.com.airescovit.clim.ui.splash.SplashMvpPresenter
import br.com.airescovit.clim.ui.splash.SplashMvpView
import br.com.airescovit.clim.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Logics on 12/01/2018.
 */
@Module
class ActivityModule(activity: AppCompatActivity) {
    var mActivity: AppCompatActivity = activity

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return mActivity
    }


    @PerActivity
    @Provides
    fun provideSplashPresenter(presenter: SplashPresenter<SplashMvpView>): SplashMvpPresenter<SplashMvpView> {
        return presenter
    }


}