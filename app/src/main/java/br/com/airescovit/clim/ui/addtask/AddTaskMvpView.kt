package br.com.airescovit.clim.ui.addtask

import br.com.airescovit.clim.ui.base.MvpView

/**
 * Created by Murilo Aires on 09/02/2018.
 */
interface AddTaskMvpView : MvpView {

    fun openSelectClientActivity()

    fun setClientName(name: String?)

    fun setDateText(date: String)

    fun onInvalidName(resId: Int)

    fun onInvalidFee(resId: Int)

    fun onInvalidDate(resId: Int)

    fun finishWithOkResult()

}