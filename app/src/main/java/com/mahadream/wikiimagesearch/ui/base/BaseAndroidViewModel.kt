package com.mahadream.wikiimagesearch.ui.base

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mahadream.wikiimagesearch.data.common.ErrorModel

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {
    open var isDataLoaded = false
    open val errorObserver = MutableLiveData<ErrorModel>()
}