package br.com.airescovit.clim.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import br.com.airescovit.clim.R

/**
 * Created by Logics on 10/01/2018.
 */
class CommomUtils {
    companion object {
        fun showLoadingDialog(context: Context): ProgressDialog? {
            val progressDialog = ProgressDialog(context)
            progressDialog.show()
            if(progressDialog.window != null){
                progressDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            progressDialog.setContentView(R.layout.progress_dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
            return progressDialog
        }

    }
}