package br.com.airescovit.clim.ui.addtask

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import br.com.airescovit.clim.ui.clients.selectclient.SelectClientActivity
import kotlinx.android.synthetic.main.activity_add_task.*
import java.util.*
import javax.inject.Inject

class AddTaskActivity : BaseActivity(), AddTaskMvpView, DatePickerDialog.OnDateSetListener {

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
    }

    val SELECT_CLIENT = 1


    @Inject
    lateinit var mPresenter: AddTaskMvpPresenter<AddTaskMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        getActivityComponent().inject(this)
        mPresenter.onAttach(this)

        layoutSelectClient.setOnClickListener({ mPresenter.onSelectClientClick() })
        editValor.addTextChangedListener(NumberTextWatcher(editValor))
        editData.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                selectDateAndHour()
            }
        }
        editData.setOnClickListener({ selectDateAndHour() })
        setSupportActionBar(toolbar)
        mPresenter.handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_clients, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.check) {
            mPresenter.onCheckClick(editTitle.text.toString(), editDescricao.text.toString(), editValor.text.toString())
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    private fun selectDateAndHour() {

        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    mPresenter.onDatePicked(year, monthOfYear, dayOfMonth)

                }, mYear, mMonth, mDay)
        datePickerDialog.datePicker.minDate = c.timeInMillis
        datePickerDialog.show()


    }


    override fun openSelectClientActivity() {
        startActivityForResult(Intent(this, SelectClientActivity::class.java), SELECT_CLIENT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SELECT_CLIENT) {
            if (resultCode == Activity.RESULT_OK) {
                mPresenter.onClientSelected(data)
            }
        }
    }

    override fun setClientName(name: String?) {
        textClientName.text = name
    }

    override fun setDateText(date: String) {
        editData.setText(date)
        hideKeyboard()
    }

    override fun onInvalidName(resId: Int) {
        textInputLayoutTaskTitle.error = getString(resId)
    }

    override fun onInvalidFee(resId: Int) {
        textInputLayoutValor.error = getString(resId)
    }

    override fun onInvalidDate(resId: Int) {
        textInputLayoutData.error = getString(resId)
    }

    override fun finishWithOkResult() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
