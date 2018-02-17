package br.com.airescovit.clim.utils

/**
 * Created by Logics on 12/01/2018.
 */
class AppConstants {

    companion object {

        const val DB_NAME: String = "clim_db"

        const val PREF_NAME: String = "clim_preferences"

        const val NULL_INDEX: Long = -1L

        const val BASE_URL: String = "https://clim-api-staging.herokuapp.com/"
        const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSZ"
        const val DATE_FORMAT_EXIBITION = "dd/MM/yyyy"
        const val API_VERSION: String = "api/v1/"

        const val SUCCESS_RESULT = 0
        const val FAILURE_RESULT = 1
        const val PACKAGE_NAME = "com.google.android.gms.location.sample.locationaddress"
        const val RECEIVER = PACKAGE_NAME + ".RECEIVER"
        const val RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY"
        const val LATITUDE_DATA_EXTRA = PACKAGE_NAME + ".LATITUDE_DATA_EXTRA"
        const val LONGITUDE_DATA_EXTRA = PACKAGE_NAME + ".LONGITUDE_DATA_EXTRA"
        const val CLIENT_ID_EXTRA = "client_id"

    }

}