package br.com.airescovit.clim.data.network

import br.com.airescovit.clim.data.network.login.endpoint.UserEndpoint
import br.com.airescovit.clim.data.network.login.model.LoginResponse
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
}