package br.com.airescovit.clim.data.content

import android.content.ContentResolver
import android.net.Uri
import android.provider.ContactsContract
import br.com.airescovit.clim.data.content.model.Contact
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by murilo aires on 11/03/2018.
 */
@Singleton
class AppContentHelper @Inject constructor(val mContentResolver: ContentResolver) : ContentHelper {

    override fun getContact(uri: Uri): Observable<Contact> {
        return Observable.fromCallable {
            val cursor = mContentResolver.query(uri, null, null, null, null)
            cursor.moveToFirst()
            val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val contactName = cursor.getString(nameIndex)
            val phoneNumberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val contactNumber = cursor.getString(phoneNumberIndex)
            cursor.close()
            Contact(contactName, contactNumber)
        }
    }

    override fun getWhatsAppProfileId(name: String): Observable<String> {
        return Observable.fromCallable {

            val cursor = mContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrayOf(ContactsContract.CommonDataKinds.Phone.CONTACT_ID),
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = ?",
                    arrayOf(name),
                    null)
            cursor.moveToFirst()
            // column index of the phone number
            val idIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            // column index of the contact name
            val contactId = cursor.getInt(idIndex)
            cursor.close()

            val contactCursor = mContentResolver.query(
                    ContactsContract.RawContacts.CONTENT_URI,
                    arrayOf(ContactsContract.RawContacts._ID), ContactsContract.RawContacts.CONTACT_ID + "= ?" + "AND " + ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",
                    arrayOf(contactId.toString(), "com.whatsapp"),
                    null)

            var rawContactId: String? = null
            if (contactCursor!!.moveToFirst()) {
                rawContactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.RawContacts._ID))
            }
            contactCursor.close()

            val contactCursor2 = mContentResolver.query(
                    ContactsContract.Data.CONTENT_URI,
                    arrayOf(ContactsContract.Data._ID), ContactsContract.Data.RAW_CONTACT_ID + "= ?" + "AND " + ContactsContract.Data.MIMETYPE + "= ?",
                    arrayOf(rawContactId, "vnd.android.cursor.item/vnd.com.whatsapp.profile"),
                    null)

            var whatsappContactId = ""
            if (contactCursor2.moveToFirst()) {
                whatsappContactId = contactCursor2.getString(contactCursor2.getColumnIndex(ContactsContract.Data._ID))
            }
            contactCursor2.close()

            whatsappContactId
        }
    }
}