package br.com.airescovit.clim.data.network

import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.data.db.model.Task
import br.com.airescovit.clim.data.network.endpoint.ClientEndpoint
import br.com.airescovit.clim.data.network.endpoint.TaskEndpoint
import br.com.airescovit.clim.data.network.endpoint.UserEndpoint
import br.com.airescovit.clim.data.network.login.model.LoginResponse
import br.com.airescovit.clim.data.network.model.RegisterClientRequest
import br.com.airescovit.clim.data.network.model.RegisterTaskRequest
import br.com.airescovit.clim.data.network.model.login.model.LoginRequest
import br.com.airescovit.clim.data.network.model.login.model.RegisterRequest
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Singleton
class AppApiHelper @Inject constructor(private val retrofit: Retrofit) : ApiHelper {


    override fun doRegisterRequest(registerRequest: Single<RegisterRequest>): Observable<LoginResponse> {
        return retrofit.create(UserEndpoint::class.java).doRegister(registerRequest)
    }

    override fun doLoginRequest(loginRequest: LoginRequest): Observable<LoginResponse> {
        return retrofit.create(UserEndpoint::class.java).doLogin(loginRequest)
    }

    override fun doRegisterClientRequest(header: String, userId: Long, registerClientRequest: RegisterClientRequest): Observable<Client> {
        return retrofit.create(ClientEndpoint::class.java).doClientRegister(header, userId, registerClientRequest)
    }

    override fun getClientsAPI(header: String, userId: Long, page: Int): Observable<List<Client>> {
        return retrofit.create(ClientEndpoint::class.java).getClients(header, userId, page)
    }

    override fun doRegisterTask(header: String, userId: Long, registerTaskRequest: RegisterTaskRequest): Observable<Task> {
        return retrofit.create(TaskEndpoint::class.java).doTaskRegister(header, userId, registerTaskRequest)
    }

    override fun getTasksAPI(header: String, userId: Long, page: Int): Observable<List<Task>> {
        return retrofit.create(TaskEndpoint::class.java).getTasks(header, userId, page)
    }
}