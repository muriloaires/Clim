package br.com.airescovit.clim.data.content

import android.net.Uri
import br.com.airescovit.clim.data.content.model.Contact
import io.reactivex.Observable

/**
 * Created by murilo aires on 11/03/2018.
 */
interface ContentHelper {

    fun getContact(uri: Uri): Observable<Contact>

    fun getWhatsAppProfileId(name: String): Observable<String>
}