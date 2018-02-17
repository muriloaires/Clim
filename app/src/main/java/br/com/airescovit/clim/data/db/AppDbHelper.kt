package br.com.airescovit.clim.data.db

import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.data.db.model.DaoMaster
import br.com.airescovit.clim.data.db.model.DaoSession
import br.com.airescovit.clim.data.db.model.Task
import io.reactivex.Observable
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
            mDaoSession.addressDao.insertOrReplace(client.address)
            mDaoSession.clientDao.insertOrReplace(client)
        })
    }

    override fun loadClient(clientId: Long): Observable<Client> {
        return Observable.fromCallable<Client>({
            mDaoSession.clientDao.load(clientId)
        })
    }

    override fun loadAllClients(): Observable<List<Client>> {
        return Observable.fromCallable<List<Client>>({
            mDaoSession.clientDao.loadAll()
        })
    }

    override fun insertClientList(clients: List<Client>): Observable<Unit> {
        return Observable.fromCallable {
            for (client: Client in clients) {
                mDaoSession.addressDao.insertOrReplace(client.address)
                mDaoSession.clientDao.insertOrReplace(client)
            }
        }
    }

    override fun insertTask(task: Task): Observable<Long> {
        return Observable.fromCallable<Long>({
            mDaoSession.addressDao.insertOrReplace(task.client.address)
            mDaoSession.clientDao.insertOrReplace(task.client)
            mDaoSession.taskDao.insertOrReplace(task)
        })

    }

    override fun insertTaskList(tasks: List<Task>): Observable<Unit> {
        return Observable.fromCallable({
            for (task: Task in tasks) {
                mDaoSession.taskDao.insertOrReplace(task)
            }
        })
    }

    override fun loadAllTasks(): Observable<List<Task>> {
        return Observable.fromCallable<List<Task>>({
            mDaoSession.taskDao.loadAll()
        })
    }
}