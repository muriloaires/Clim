package br.com.airescovit.clim.ui.clients.addclients

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import br.com.airescovit.clim.R
import br.com.airescovit.clim.services.AddressIntentService
import br.com.airescovit.clim.ui.base.BaseActivity
import br.com.airescovit.clim.utils.AppConstants
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : BaseActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnInfoWindowClickListener {

    companion object {
        val REQUEST_FINE_LOCATION: Int = 1
        val LOCATION_UPDATE_REQUEST: Int = 2
    }

    private lateinit var mMap: GoogleMap
    private var mGoogleApiClient: GoogleApiClient? = null
    private lateinit var mLocationRequest: LocationRequest
    private var mLastLocation: Location? = null
    private var addressResultReceiver: AddressResultReceiver? = null
    private var mAddressOutput: Address? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        addressResultReceiver = AddressResultReceiver(Handler())
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_FINE_LOCATION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    buildGoogleApiClient()
                    mMap.isMyLocationEnabled = true
                } else {
                    showMessage(R.string.no_fine_location_permission)
                }
            }
            else -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
                } else {
                    showMessage(R.string.no_fine_location_permission)
                }
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnInfoWindowClickListener(this)
        mMap.setOnCameraMoveListener {
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(mMap.cameraPosition.target).title("Toque aqui para").snippet("Selecionar a posição do cliente")).showInfoWindow()

        }
        if (isLowerThanMarshmallow()) {
            buildGoogleApiClient()
            mMap.isMyLocationEnabled = true
        } else {
            if (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                buildGoogleApiClient()
                mMap.isMyLocationEnabled = true
            } else {
                requestPermissionSafely(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_FINE_LOCATION)
            }
        }

    }


    @Synchronized
    protected fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
        mGoogleApiClient?.connect()
    }


    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {
        mLocationRequest = LocationRequest()
        mLocationRequest.smallestDisplacement = 20f
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if (isLowerThanMarshmallow()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
        } else {
            if (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
            } else {
                requestPermissionSafely(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_UPDATE_REQUEST)
            }

        }
    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }


    override fun onLocationChanged(location: Location?) {
        mLastLocation = location
        val latLng = LatLng(location!!.latitude, location.longitude)
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f))
    }


    override fun onInfoWindowClick(marker: Marker?) {
        val intent = Intent(this, AddressIntentService::class.java)
        intent.putExtra(AppConstants.RECEIVER, addressResultReceiver)
        intent.putExtra(AppConstants.LATITUDE_DATA_EXTRA, marker?.position?.latitude)
        intent.putExtra(AppConstants.LONGITUDE_DATA_EXTRA, marker?.position?.longitude)
        startService(intent)
    }


    internal inner class AddressResultReceiver(handler: Handler) : ResultReceiver(handler) {

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {

            // Display the address string
            // or an error message sent from the intent service.
            mAddressOutput = resultData!!.getParcelable(AppConstants.RESULT_DATA_KEY)
            val intent = Intent()
            intent.putExtra(AppConstants.RESULT_DATA_KEY, mAddressOutput)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
