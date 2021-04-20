package com.mahadream.wikiimagesearch.utils.dialogutills

import android.app.Dialog
import android.content.Context
import android.icu.text.Normalizer.NO
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.mahadream.wikiimagesearch.R

class DialogUtills {
    companion object {
        private var dialog: Dialog? = null

        @JvmStatic
        public fun showDialog(dialogData: DialogData, context: Context, listner: IDialogListner?) {
            dialog = Dialog(context)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setCancelable(false)
            dialog?.setContentView(R.layout.custom_dialog_layout)
            val body = dialog?.findViewById(R.id.tvBody) as TextView
            val title = dialog?.findViewById<TextView>(R.id.tvTitle) as TextView

            if (!dialogData.mDescription.isNullOrEmpty())
                body.text = dialogData.mDescription

            if (!dialogData.mTitle.isNullOrEmpty())
                title.text = dialogData.mTitle

            val yesBtn = dialog?.findViewById(R.id.btn_yes) as Button
            val noButon =  dialog?.findViewById<Button>(R.id.btn_no) as Button

            if (!dialogData.mOkButtonText.isNullOrEmpty())
                yesBtn.setText(dialogData.mOkButtonText)

            if (dialogData.isButtonsAvailable) {
                dialog?.findViewById<LinearLayout>(R.id.button_container)?.visibility = View.VISIBLE
                if (!dialogData.mOkButtonText.isNullOrEmpty()) {
                    yesBtn.visibility = View.VISIBLE
                    yesBtn.setText(dialogData.mOkButtonText)
                } else {
                    yesBtn.visibility = View.GONE
                }
                if (!dialogData.mNoButtonText.isNullOrEmpty()) {
                    noButon.visibility = View.VISIBLE
                    noButon.setText(dialogData.mNoButtonText)
                } else {
                    noButon?.visibility = View.GONE
                }
            }

            dialogData.isCancelable?.let { dialog?.setCancelable(it) }
            yesBtn.setOnClickListener {
                listner?.onPosetiveButtonClick()
                dialog?.dismiss()
            }
            noButon.setOnClickListener {
                listner?.onNegativeButtonClick()
                dialog?.dismiss()
            }
            dialog?.show()
        }

        fun getNoNetworkDialogData(context: Context): DialogData {
            return DialogData.Builder()
                .setDescription(context.resources.getString(R.string.no_network_desc))
                .setTitle(context.resources.getString(R.string.no_network_title))
                .setIsButtonAvailable(true)
                .setIsCancelable(true).build()
        }

        fun getNoApiError(context: Context): DialogData {
            return DialogData.Builder()
                .setDescription(context.resources.getString(R.string.api_error_description))
                .setTitle(context.resources.getString(R.string.api_error))
                .setIsButtonAvailable(true)
                .setOkButtonText(context.resources.getString(R.string.retry))
                .setIsCancelable(true).build()
        }

        fun getAppQuitDialog(context: Context): DialogData {
            return DialogData.Builder()
                .setDescription(context.resources.getString(R.string.app_quit_descriopn))
                .setTitle(context.resources.getString(R.string.app_quit_title))
                .setIsButtonAvailable(true)
                .setOkButtonText(context.resources.getString(R.string.ok))
                .setNoButtonText(context.resources.getString(R.string.no))
                .setIsCancelable(true).build()
        }

        fun showNoNetworkDialog(context: Context) {
            showDialog(getNoNetworkDialogData(context), context, null)
        }

        fun showAppQuitDialog(context: Context, listner: IDialogListner) {
            showDialog(getAppQuitDialog(context), context, listner)
        }

        fun showApiError(context: Context, listner: IDialogListner) {
            showDialog(getNoApiError(context), context, null)
        }

        fun removeDialog() {
            if (dialog != null && dialog?.isShowing!!) {
                dialog?.dismiss()
            }
        }
    }

}