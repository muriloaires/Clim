package br.com.airescovit.clim.ui.clients.selectclient

import android.content.Intent
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.data.db.model.Client
import br.com.airescovit.clim.ui.base.BasePresenter
import br.com.airescovit.clim.ui.clients.ClientsListAction
import br.com.airescovit.clim.utils.AppConstants
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by murilo aires on 12/02/2018.
 */
class SelectClientPresenter<V : SelectClientMvpView> @Inject constructor(dataManager: DataManager, compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), SelectClientMvpPresenter<V>, ClientsListAction {


    private var clients: MutableList<Any?> = mutableListOf()
    private var page = 1

    init {
        clients.add(0, Any())
        clients.add(clients.size, null)
    }

    override fun onAddClientsActivityReturn() {
        loadAllClientsFromDb()
    }

    private fun loadAllClientsFromDb() {
        dataManager.loadAllClients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it: List<Client> ->
                    if (!it.isEmpty()) {
                        getMvpView()?.hideNoClientView()
                    }
                    this.clients.addAll(this.clients.size - 1, it)
                    getMvpView()?.updateClientsList()
                    loadFromAPI(page)
                })
    }

    override fun onFabClick() {
        getMvpView()?.openAddClientsActivity()
    }

    override fun onViewReady() {
        loadAllClientsFromDb()
    }

    private fun loadFromAPI(page: Int) {
        getMvpView()?.resetScrollListener()
        dataManager.getClientsAPI(dataManager.getCurrentAccessToken(), dataManager.getCurrentUserId(), page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ clients ->
                    for (client: Client in clients) {
                        client.attachEntities()
                    }
                    dataManager.insertClientList(clients)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()

                    if (this.page == 1) {
                        getMvpView()?.setOnScrollListener()
                        this.clients.clear()
                        this.clients.add(Any())
                        this.clients.add(null)


                        if (clients.isEmpty()) {
                            getMvpView()?.showNoClientsView()
                        }
                    }

                    if (clients.size < 20) {
                        this.clients.remove(null)
                        this.clients.addAll(this.clients.size, clients)
                        getMvpView()?.removeScrollListener()
                    } else {
                        this.clients.addAll(this.clients.size - 1, clients)
                        this.page++
                    }

                    getMvpView()?.updateClientsList()
                }, { err ->
                    handleApiError(err as HttpException)
                })
    }

    override fun onRecylerLoadmore() {
        page++
        loadFromAPI(page)
    }

    override fun getList(): List<Any?> {
        return this.clients
    }

    override fun onAddClientClick() {
        getMvpView()?.openAddClientsActivity()
    }

    override fun isSelection(): Boolean = true

    override fun onAddTaskClick(position: Int) {
        //Does nothing
    }

    override fun onItemClick(position: Int) {
        val data = Intent()
        data.putExtra(AppConstants.CLIENT_ID_EXTRA, (clients[position] as Client).id)
        getMvpView()?.finishWithOkResult(data)
    }

    override fun getClientName(position: Int): String {
        return (clients[position] as Client).name
    }

    override fun getClientStateCity(position: Int): String {
        return try {
            (clients[position] as Client).address.state + " - " + (clients[position] as Client).address.city
        } catch (e: Exception) {
            " - "
        }
    }

    override fun getClientStreetNeighborhood(position: Int): String {
        return try {
            (clients[position] as Client).address.street + " - " + (clients[position] as Client).address.neighborhood
        } catch (e: Exception) {
            " - "
        }
    }

    override fun getClientZipCode(position: Int): String {
        return try {
            (clients[position] as Client).address.postalCode
        } catch (e: Exception) {
            " - "
        }
    }
}