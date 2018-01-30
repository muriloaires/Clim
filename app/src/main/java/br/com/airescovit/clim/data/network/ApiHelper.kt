package br.com.airescovit.clim.data.network

import br.com.airescovit.clim.data.network.login.model.LoginResponse
import br.com.airescovit.clim.data.network.model.login.model.LoginRequest
import br.com.airescovit.clim.data.network.model.login.model.RegisterRequest
import io.reactivex.Observable

/**
 * Created by Logics on 12/01/2018.
 */
interface ApiHelper {

    fun doLoginRequest(loginRequest: LoginRequest): Observable<LoginResponse>

    fun doRegisterRequest(registerRequest: Single<RegisterRequest>): Observable<LoginResponse>
}