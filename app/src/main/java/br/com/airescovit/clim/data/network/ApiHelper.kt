package br.com.airescovit.clim.data.network

import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.data.db.model.Task
import br.com.airescovit.clim.data.network.login.model.LoginResponse
import br.com.airescovit.clim.data.network.model.RegisterClientRequest
import br.com.airescovit.clim.data.network.model.RegisterTaskRequest
import br.com.airescovit.clim.data.network.model.login.model.LoginRequest
import br.com.airescovit.clim.data.network.model.login.model.RegisterRequest
import io.reactivex.Observable

/**
 * Created by Logics on 12/01/2018.
 */
interface ApiHelper {

    fun doLoginRequest(loginRequest: LoginRequest): Observable<LoginResponse>

    fun doRegisterRequest(registerRequest: Single<RegisterRequest>): Observable<LoginResponse>


    fun doRegisterClientRequest(header: String, userId: Long, registerClientRequest: RegisterClientRequest): Observable<Client>

    fun getClientsAPI(header: String, userId: Long, page: Int): Observable<List<Client>>


    fun doRegisterTask(header: String, clientId: Long, registerTaskRequest: RegisterTaskRequest): Observable<Task>

    fun getTasksAPI(header: String, userId: Long, page: Int): Observable<List<Task>>
}