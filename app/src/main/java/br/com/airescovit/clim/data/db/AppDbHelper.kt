package br.com.airescovit.clim.data.db

import br.com.airescovit.clim.data.db.model.DaoMaster
import br.com.airescovit.clim.data.db.model.DaoSession
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Singleton
class AppDbHelper @Inject constructor(dbOpenHelper: DbOpenHelper): DbHelper {

    private var mDaoSession: DaoSession = DaoMaster(dbOpenHelper.writableDatabase).newSession()

}