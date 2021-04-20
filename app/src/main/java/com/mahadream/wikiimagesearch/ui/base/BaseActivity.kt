package com.mahadream.wikiimagesearch.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mahadream.wikiimagesearch.utils.dialogutills.DialogUtills
import com.mahadream.wikiimagesearch.utils.dialogutills.IDialogListner

open class BaseActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showProgress() {
        if (!isFinishing) {
            if (!::progressDialog.isInitialized) {
                progressDialog = ProgressDialog(this)
            }
            progressDialog.show()
        }
    }

    fun cancelProgress() {
        if (!isFinishing && ::progressDialog.isInitialized && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    override fun onBackPressed() {
        DialogUtills.showAppQuitDialog(this, object : IDialogListner {
            override fun onPosetiveButtonClick() {
                finish()
            }

            override fun onNegativeButtonClick() {
            }

        })
    }
}