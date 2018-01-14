package br.com.airescovit.clim.data.db

import android.content.Context
import br.com.airescovit.clim.data.db.model.DaoMaster
import org.greenrobot.greendao.database.Database

/**
 * Created by Logics on 12/01/2018.
 */
class DbOpenHelper(context: Context?, name: String?) : DaoMaster.OpenHelper(context, name) {

    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
    }
}