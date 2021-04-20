package com.mahadream.wikiimagesearch.utils.dialogutills

class DialogData(
    val mTitle: String?,
    val  mDescription: String?,
    val mOkButtonText: String?,
    val mNoButtonText: String?,
    val isCancelable: Boolean = false,
    val isButtonsAvailable: Boolean = true
) {

    data class Builder(
        var mTitle: String? = null,
        var mDescription: String? = null,
        var mOkButtonText: String? = null,
        var mNoButtonText: String? = null,
        var mIsCancelable: Boolean = false,
        var mIsButtonAvailable: Boolean = false
    ) {

        fun setTitle(title: String) = apply { this.mTitle = title }
        fun setDescription(desc: String) = apply { this.mDescription = desc }
        fun setOkButtonText(okButtonText: String) = apply { this.mOkButtonText = okButtonText }
        fun setNoButtonText(noButtonText: String) = apply { this.mNoButtonText = noButtonText }
        fun setIsCancelable(isCancelable: Boolean) = apply { this.mIsCancelable = isCancelable }
        fun setIsButtonAvailable(isButtonsAvailable: Boolean) =
            apply { this.mIsButtonAvailable = isButtonsAvailable }

        fun build() = DialogData(mTitle, mDescription, mOkButtonText, mNoButtonText, mIsCancelable,mIsButtonAvailable)
    }
}
