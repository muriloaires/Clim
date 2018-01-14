package br.com.airescovit.clim.di.module

import android.app.Application
import android.content.Context
import br.com.airescovit.clim.ClimApp
import br.com.airescovit.clim.data.AppDataManager
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.data.db.AppDbHelper
import br.com.airescovit.clim.data.db.DbHelper
import br.com.airescovit.clim.data.prefs.AppPreferenceHelper
import br.com.airescovit.clim.data.prefs.PreferenceHelper
import br.com.airescovit.clim.di.ApplicationContext
import br.com.airescovit.clim.di.DatabaseInfo
import br.com.airescovit.clim.di.PreferenceInfo
import br.com.airescovit.clim.utils.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Module
class ApplicationModule(val application: ClimApp) {
    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }


    @Provides
    @DatabaseInfo
    fun providesDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @PreferenceInfo
    fun providePrerenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper {
        return appPreferenceHelper
    }
}