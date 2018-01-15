package br.com.airescovit.clim.data.db

import android.content.Context
import br.com.airescovit.clim.data.db.model.DaoMaster
import br.com.airescovit.clim.di.ApplicationContext
import br.com.airescovit.clim.di.DatabaseInfo
import org.greenrobot.greendao.database.Database
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Singleton
class DbOpenHelper @Inject constructor(@ApplicationContext context: Context?, @DatabaseInfo name: String?) : DaoMaster.OpenHelper(context, name) {

    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
    }
}