package br.com.airescovit.clim.data.db

import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.data.db.model.DaoMaster
import br.com.airescovit.clim.data.db.model.DaoSession
import io.reactivex.Observable
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Singleton
class AppDbHelper @Inject constructor(dbOpenHelper: DbOpenHelper) : DbHelper {


    private var mDaoSession: DaoSession = DaoMaster(dbOpenHelper.writableDatabase).newSession()

    override fun insertClient(client: Client): Observable<Long> {
        return Observable.fromCallable<Long>({
            mDaoSession.clientDao.insert(client)
        })
    }

    override fun loadAllClients(): Observable<List<Client>> {
        return Observable.fromCallable<List<Client>>({
            mDaoSession.clientDao.loadAll()
        })
    }
}