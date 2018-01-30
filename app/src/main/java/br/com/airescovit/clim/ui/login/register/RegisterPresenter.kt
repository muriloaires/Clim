package br.com.airescovit.clim.ui.login.register

import br.com.airescovit.clim.R
import br.com.airescovit.clim.data.DataManager
import br.com.airescovit.clim.data.network.Single
import br.com.airescovit.clim.data.network.model.login.model.RegisterRequest
import br.com.airescovit.clim.ui.base.BasePresenter
import br.com.airescovit.clim.utils.TextUtils
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Logics on 16/01/2018.
 */
class RegisterPresenter<V : RegisterMvpView> @Inject constructor(dataManager: DataManager) : BasePresenter<V>(dataManager), RegisterMvpPresenter<V> {

    override fun onBtnRegisterClick(firstName: String, lastName: String, email: String, password: String, confirmPassword: String) {
        when {
            firstName.isEmpty() -> getMvpView()?.onIncorrectName(R.string.compulsory_field)
            email.isEmpty() -> getMvpView()?.onIncorrectEmail(R.string.compulsory_field)
            !TextUtils.validEmail(email) -> getMvpView()?.onIncorrectEmail(R.string.incorrect_email)
            password.isEmpty() -> getMvpView()?.onIncorrectPassword(R.string.compulsory_field)
            password.length < 6 -> getMvpView()?.onIncorrectPassword(R.string.min_length_password_error)
            password != confirmPassword -> getMvpView()?.onDifferentsPasswords(R.string.different_password)
            else -> {

                getMvpView()?.hideKeyboard()
                getMvpView()?.showLoading()
                val observable = dataManager.doRegisterRequest(Single(RegisterRequest(firstName, lastName, email, password, confirmPassword)))
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ registerResponse ->
                            dataManager.updateUserInfo(registerResponse.token, registerResponse.user.id,
                                    DataManager.LoginMode.LOGGE_IN_MODE_SERVER,
                                    registerResponse.user.firstName + registerResponse.user.lastName,
                                    registerResponse.user.email)
                            getMvpView()?.hideLoading()
                            getMvpView()?.startMainActivity()
                        }, { err ->
                            getMvpView()?.hideLoading()
                            handleApiError(err as HttpException)
                        })
            }
        }
    }
}