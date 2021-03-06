package br.com.airescovit.clim.data

import android.content.Context
import android.net.Uri
import br.com.airescovit.clim.data.content.ContentHelper
import br.com.airescovit.clim.data.content.model.Contact
import br.com.airescovit.clim.data.db.DbHelper
import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.data.db.model.Task
import br.com.airescovit.clim.data.network.ApiHelper
import br.com.airescovit.clim.data.network.Single
import br.com.airescovit.clim.data.network.login.model.LoginResponse
import br.com.airescovit.clim.data.network.model.RegisterClientRequest
import br.com.airescovit.clim.data.network.model.RegisterTaskRequest
import br.com.airescovit.clim.data.network.model.login.model.LoginRequest
import br.com.airescovit.clim.data.network.model.login.model.RegisterRequest
import br.com.airescovit.clim.data.prefs.PreferenceHelper
import br.com.airescovit.clim.di.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Logics on 12/01/2018.
 */
@Singleton
class AppDataManager @Inject constructor(@ApplicationContext val context: Context, val mDbHelper: DbHelper,
                                         val mPreferencesHelper: PreferenceHelper, val mApiHelper: ApiHelper,
                                         val mContentHelper: ContentHelper) : DataManager {


    override fun setUserAsLoggedOut() {
        updateUserInfo(null, null, DataManager.LoginMode.LOGGED_IN_MODE_LOGGED_OUT, null, null)
    }

    override fun updateUserInfo(accessToken: String?, userId: Long?, loginMode: DataManager.LoginMode, userName: String?, userEmail: String?) {
        setAccessToken(accessToken)
        setCurrentUserId(userId)
        setCurrentLoginMode(loginMode)
        setCurrentUserName(userName)
        setCurrentUserEmail(userEmail)

    }

    override fun getCurrentUserId(): Long {
        return mPreferencesHelper.getCurrentUserId()
    }

    override fun setCurrentUserId(id: Long?) {
        mPreferencesHelper.setCurrentUserId(id)
    }

    override fun getCurrentUserName(): String {
        return mPreferencesHelper.getCurrentUserName()
    }

    override fun setCurrentUserName(name: String?) {
        mPreferencesHelper.setCurrentUserName(name)
    }

    override fun setCurrentUserEmail(email: String?) {
        mPreferencesHelper.setCurrentUserEmail(email)
    }

    override fun getCurrentUserEmail(): String {
        return mPreferencesHelper.getCurrentUserEmail()
    }

    override fun setAccessToken(accessToken: String?) {
        mPreferencesHelper.setAccessToken(accessToken)
    }

    override fun getCurrentAccessToken(): String {
        return mPreferencesHelper.getCurrentAccessToken()
    }

    override fun getCurrentLoginMode(): Int {
        return mPreferencesHelper.getCurrentLoginMode()
    }

    override fun setCurrentLoginMode(loginMode: DataManager.LoginMode) {
        mPreferencesHelper.setCurrentLoginMode(loginMode)
    }

    override fun doLoginRequest(loginRequest: LoginRequest): Observable<LoginResponse> {
        return mApiHelper.doLoginRequest(loginRequest)
    }

    override fun doRegisterRequest(registerRequest: Single<RegisterRequest>): Observable<LoginResponse> {
        return mApiHelper.doRegisterRequest(registerRequest)
    }

    override fun doRegisterClientRequest(header: String, userId: Long, registerClientRequest: RegisterClientRequest): Observable<Client> {
        return mApiHelper.doRegisterClientRequest(header, userId, registerClientRequest)
    }

    override fun getClientsAPI(header: String, userId: Long, page: Int): Observable<List<Client>> {
        return mApiHelper.getClientsAPI(header, userId, page)
    }

    override fun insertClient(client: Client): Observable<Long> {
        return mDbHelper.insertClient(client)
    }

    override fun insertClientList(clients: List<Client>): Observable<Unit> {
        return mDbHelper.insertClientList(clients)
    }

    override fun loadAllClients(): Observable<List<Client>> {
        return mDbHelper.loadAllClients()
    }

    override fun loadClient(clientId: Long): Observable<Client> {
        return mDbHelper.loadClient(clientId)
    }

    override fun doRegisterTask(header: String, clientId: Long, registerTaskRequest: RegisterTaskRequest): Observable<Task> {
        return mApiHelper.doRegisterTask(header, clientId, registerTaskRequest)
    }

    override fun insertTask(task: Task): Observable<Long> {
        return mDbHelper.insertTask(task)
    }

    override fun insertTaskList(tasks: List<Task>): Observable<Unit> {
        return mDbHelper.insertTaskList(tasks)
    }

    override fun loadAllTasks(): Observable<List<Task>> {
        return mDbHelper.loadAllTasks()
    }

    override fun getTasksAPI(header: String, userId: Long, page: Int): Observable<List<Task>> {
        return mApiHelper.getTasksAPI(header, userId, page)
    }

    override fun getContact(uri: Uri): Observable<Contact> {
        return mContentHelper.getContact(uri)
    }

    override fun getWhatsAppProfileId(name: String): Observable<String> {
        return mContentHelper.getWhatsAppProfileId(name)
    }
}