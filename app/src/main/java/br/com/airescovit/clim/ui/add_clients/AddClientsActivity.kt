package br.com.airescovit.clim.ui.add_clients

import android.app.Activity
import android.content.Intent
import android.location.Address
import android.os.Bundle
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

    private var address: Address? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_clients)
        getActivityComponent().inject(this)
        mPresenter.onAttach(this)
        setSupportActionBar(toolbarAddClients)
        btnMarcarNoMapa.setOnClickListener({ startActivityForResult(Intent(this, MapActivity::class.java), REQUEST_ADDRESS) })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_clients, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.check -> {
                showMessage("gigigi")
            }
        }
        return super.onOptionsItemSelected(item)
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
}
