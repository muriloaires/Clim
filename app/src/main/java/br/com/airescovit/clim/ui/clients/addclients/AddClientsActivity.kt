package br.com.airescovit.clim.ui.clients.addclients

import android.app.Activity
import android.content.Intent
import android.location.Address
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.Menu
import android.view.MenuItem
import br.com.airescovit.clim.R
import br.com.airescovit.clim.ui.base.BaseActivity
import br.com.airescovit.clim.utils.AppConstants
import kotlinx.android.synthetic.main.activity_add_clients.*
import javax.inject.Inject


class AddClientsActivity : BaseActivity(), AddClientsMvpView {


    companion object {
        const val REQUEST_ADDRESS: Int = 0
    }

    @Inject
    lateinit var mPresenter: AddClientsMvpPresenter<AddClientsMvpView>
    private lateinit var countriesString: List<String>
    private var address: Address? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_clients)
        getActivityComponent().inject(this)
        mPresenter.onAttach(this)
        setSupportActionBar(toolbarAddClients)
        mPresenter.loadCountries()
        btnMarcarNoMapa.setOnClickListener({ startActivityForResult(Intent(this, MapActivity::class.java), REQUEST_ADDRESS) })
        inputClientPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_clients, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.check -> consume {
            mPresenter.onCheckbuttonClick(inputClientName.text.toString(), inputClientPhone.text.toString(), inputPostalCode.text.toString(), inputState.text.toString(), inputCity.text.toString(), inputNeighborhood.text.toString(), inputStreet.text.toString(), inputComplemento.text.toString(), inputNumber.text.toString(), address?.latitude, address?.longitude, spinnerCountries.selectedCountryName)
        }
        else -> super.onOptionsItemSelected(item)
    }

    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ADDRESS) {
            if (resultCode == Activity.RESULT_OK) {
                address = data?.getParcelableExtra(AppConstants.RESULT_DATA_KEY)
                populateFieldWithAddress()
            }
        }
    }

    private fun populateFieldWithAddress() {
        inputPostalCode.setText(address?.postalCode)
        inputState.setText(address?.adminArea)
        inputCity.setText(address?.subAdminArea)
        inputNeighborhood.setText(address?.subLocality)
        inputStreet.setText(address?.thoroughfare)
    }

    override fun onIncorrectClientName(resId: Int) {
        textInputLayoutClientName.error = getString(resId)
    }

    override fun onIncorrectClientPhone(resId: Int) {
        textInputLayoutClientPhone.error = getString(resId)
    }


    override fun onIncorrectState(resId: Int) {
        textInputLayoutClientState.error = getString(resId)
    }

    override fun onIncorrectCity(resId: Int) {
        textInputLayoutClientCity.error = getString(resId)
    }

    override fun onIncorrectStreet(resId: Int) {
        textInputLayoutClientStreet.error = getString(resId)
    }

    override fun onIncorrectCountry(resId: Int) {
        showMessage(resId)
    }

    override fun configureSpinners(countriesString: List<String>, defaultPosition: Int) {
    }

    override fun finishWithOkResult() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
