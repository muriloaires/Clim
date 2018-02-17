package br.com.airescovit.clim.data.network.endpoint

import br.com.airescovit.clim.data.db.model.Task
import br.com.airescovit.clim.data.network.model.RegisterTaskRequest
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Murilo Aires on 07/02/2018.
 */
interface TaskEndpoint {

    companion object {
        const val TASK_CLIENT_ENDPOINT: String = "clients/{clientId}/tasks"
        const val TASK_ENDPOINT: String = "users/{userId}/tasks"
    }

    @POST(TASK_CLIENT_ENDPOINT)
    fun doTaskRegister(@Header("Authorization") header: String, @Path("clientId") clientId: Long, @Body registerTaskRequest: RegisterTaskRequest): Observable<Task>

    @GET(TASK_ENDPOINT)
    fun getTasks(@Header("Authorization") header: String, @Path("userId") clientId: Long, @Query("page") page: Int): Observable<List<Task>>
}