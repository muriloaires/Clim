package br.com.airescovit.clim.services

import android.app.IntentService
import android.content.ContentValues.TAG
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import br.com.airescovit.clim.R
import br.com.airescovit.clim.utils.AppConstants
import java.io.IOException
import java.util.*


/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 */
class AddressIntentService : IntentService("AddressIntentService") {

    private var addresses: List<Address>? = null
    private var mReceiver: ResultReceiver? = null
    override fun onHandleIntent(intent: Intent?) {
        val geocoder = Geocoder(this, Locale.getDefault())
        var errorMessage = ""

        // Get the location passed to this service through an extra.
        val latitude: Double? = intent?.getDoubleExtra(AppConstants.LATITUDE_DATA_EXTRA, 0.0)
        val longitude: Double? = intent?.getDoubleExtra(AppConstants.LONGITUDE_DATA_EXTRA, 0.0)
        mReceiver = intent?.getParcelableExtra(AppConstants.RECEIVER)


        try {
            addresses = geocoder.getFromLocation(latitude!!, longitude!!, 1)
        } catch (ioException: IOException) {
            // Catch network or other I/O problems.
            errorMessage = getString(R.string.service_not_available)
            Log.e(TAG, errorMessage, ioException)
        } catch (illegalArgumentException: IllegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = getString(R.string.invalid_lat_long_used)
        }


        // Handle case where no address was found.
        if (addresses == null || addresses!!.isEmpty()) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found)
                Log.e(TAG, errorMessage)
            }
            deliverResultErrorToReceiver(AppConstants.FAILURE_RESULT, errorMessage)
        } else {
            val address = addresses!![0]
            deliverResultToReceiver(AppConstants.SUCCESS_RESULT, address)
        }
    }

    private fun deliverResultErrorToReceiver(resultCode: Int, errorMessage: String) {
        val bundle = Bundle()
        bundle.putString(AppConstants.RESULT_DATA_KEY, errorMessage)
        mReceiver?.send(resultCode, bundle)
    }


    private fun deliverResultToReceiver(resultCode: Int, address: Address) {
        val bundle = Bundle()
        bundle.putParcelable(AppConstants.RESULT_DATA_KEY, address)
        mReceiver?.send(resultCode, bundle)
    }
}
