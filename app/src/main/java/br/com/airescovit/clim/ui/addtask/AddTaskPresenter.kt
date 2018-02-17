package br.com.airescovit.clim.ui.addtask

import android.content.Intent
import br.com.airescovit.clim.R
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.data.network.model.RegisterTaskRequest
import br.com.airescovit.clim.data.network.model.TaskRequest
import br.com.airescovit.clim.ui.base.BasePresenter
import br.com.airescovit.clim.utils.AppConstants
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


/**
 * Created by Murilo Aires on 09/02/2018.
 */
class AddTaskPresenter<V : AddTaskMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), AddTaskMvpPresenter<V> {

    private var clientId: Long? = null
    private var selectedDate: Date? = null

    override fun onSelectClientClick() {
        getMvpView()?.openSelectClientActivity()
    }

    override fun handleIntent(intent: Intent?) {
        if (intent != null) {
            val clientId: Long = intent.getLongExtra(AppConstants.CLIENT_ID_EXTRA, -1L)
            if (clientId != -1L) {
                this.clientId = clientId
                dataManager.loadClient(clientId!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { client ->
                            getMvpView()?.setClientName(client.name)
                        }
            }
        }

    }

    override fun onClientSelected(data: Intent?) {
        clientId = data?.getLongExtra(AppConstants.CLIENT_ID_EXTRA, -1L)
        dataManager.loadClient(clientId!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { client ->
                    getMvpView()?.setClientName(client.name)
                }
    }

    override fun onDatePicked(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, monthOfYear)
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        selectedDate = c.time
        val format = SimpleDateFormat(AppConstants.DATE_FORMAT_EXIBITION, Locale.getDefault())
        val dateString = format.format(selectedDate)
        getMvpView()?.setDateText(dateString)

    }

    override fun onCheckClick(title: String, description: String, valor: String) {
        when {
            clientId == null -> getMvpView()?.showMessage(R.string.pick_a_cliente)
            title.isEmpty() -> getMvpView()?.onInvalidName(R.string.compulsory_field)
            valor.replace(Currency.getInstance(Locale.getDefault()).symbol, "", true).isEmpty() -> getMvpView()?.onInvalidFee(R.string.compulsory_field)
            selectedDate == null -> getMvpView()?.onInvalidDate(R.string.compulsory_field)
            else -> {
                getMvpView()?.hideKeyboard()
                getMvpView()?.showLoading()
                val format = NumberFormat.getInstance(Locale.getDefault())
                val fee = format.parse(valor.replace(Currency.getInstance(Locale.getDefault()).symbol, "", true))

                dataManager.doRegisterTask(dataManager.getCurrentAccessToken(), clientId!!,
                        RegisterTaskRequest(TaskRequest(title, description, selectedDate!!, null, fee.toDouble())))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ task ->
                            getMvpView()?.hideLoading()
                            task.attachEntities()
                            dataManager.insertTask(task).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe { getMvpView()?.finishWithOkResult() }


                        }, { err ->
                            getMvpView()?.hideLoading()
                            handleApiError(err as HttpException)
                        })
            }
        }
    }
}