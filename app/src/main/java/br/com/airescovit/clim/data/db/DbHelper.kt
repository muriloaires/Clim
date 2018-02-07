package br.com.airescovit.clim.data.db

import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.data.db.model.Task
import io.reactivex.Observable

/**
 * Created by Logics on 12/01/2018.
 */
interface DbHelper {
    fun insertClient(client: Client): Observable<Long>
    fun insertClientList(clients: List<Client>): Observable<Unit>
    fun loadAllClients(): Observable<List<Client>>

    fun insertTask(task: Task): Observable<Long>
    fun insertTaskList(tasks: List<Task>): Observable<Unit>
    fun loadAllTasks(): Observable<List<Task>>
}