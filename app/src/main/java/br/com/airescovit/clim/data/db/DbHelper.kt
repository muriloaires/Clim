package br.com.airescovit.clim.data.db

import br.com.airescovit.clim.data.db.model.Client
import io.reactivex.Observable

/**
 * Created by Logics on 12/01/2018.
 */
interface DbHelper {
    fun insertClient(client: Client): Observable<Long>
    fun loadAllClients(): Observable<List<Client>>
}