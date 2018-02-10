package br.com.airescovit.clim.ui.addtask

import android.os.Bundle
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import javax.inject.Inject

class AddTaskActivity : BaseActivity(), AddTaskMvpView {

    @Inject lateinit var mPresenter: AddTaskMvpPresenter<AddTaskMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        getActivityComponent().inject(this)
        mPresenter.onAttach(this)
    }
}
