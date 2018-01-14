package br.com.airescovit.clim.ui.base

import br.com.airescovit.clim.data.DataManager
import javax.inject.Inject

/**
 * Created by Logics on 12/01/2018.
 */
open class BasePresenter <in V : MvpView> @Inject constructor(dataManager: DataManager) : MvpPresenter<V> {

     var mDataManager: DataManager = dataManager

    private var mMvpView: V? = null
    override fun onAttach(mvpView: V) {
        mMvpView = mvpView
    }

    override fun onDetach() {
        mMvpView = null
    }

    override fun handleApiError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUserAsLoggedOut() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun isViewAttached(): Boolean {
        return mMvpView != null
    }

    fun getMvpView(): MvpView? {
        return mMvpView
    }
}