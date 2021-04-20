package com.mahadream.wikiimagesearch.data.repositories

import com.mahadream.wikiimagesearch.data.common.BaseApiResponseModel
import com.designwebtech.kambala.android.data.network.models.response.Either
import com.mahadream.wikiimagesearch.data.common.ErrorModel
import com.mahadream.wikiimagesearch.utils.AppConstants
import retrofit2.Response
import java.net.HttpURLConnection


/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseRepository {

    fun <ApiModel, UiModel> processRequest(
        response: Response<BaseApiResponseModel<ApiModel>>,
        convert: (data: ApiModel?) -> UiModel
    ): Either<ErrorModel, UiModel> {
        try {
            response.takeIf { it.isSuccessful }?.body()?.apply {
                return if (data != null) {
                    Either.Right(convert(data))
                } else {
                    when {
                        statusCode != null && message != null -> {
                            Either.Left(ErrorModel(statusCode, message))
                        }
                        else -> {
                            Either.Left(getGenericError())
                        }
                    }
                }
            } ?: apply {
                return when (val code = response.code()) {
                    HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        Either.Left(ErrorModel(code.toString(), "Session expired!"))
                    }
                    HttpURLConnection.HTTP_GONE -> {
                        Either.Left(ErrorModel(code.toString(), response.message()))
                    }
                    else -> Either.Left(getGenericError())
                }
            }
        } catch (e: Exception) {
            return Either.Left(getGenericError())
        } catch (e: Throwable) {
            return Either.Left(getGenericError())
        }
        return Either.Left(getGenericError())
    }

    fun <ApiModel, UiModel> processRequestWithoutData(
        response: Response<ApiModel>,
        convert: (response: ApiModel?) -> UiModel
    ): Either<ErrorModel, UiModel> {
        try {
            response.takeIf { it.isSuccessful }?.body()?.apply {
                return if (response.body() != null) {
                    Either.Right(convert(response.body()))
                } else {
                    when {
                        response.code() != null && response.message() != null -> {
                            Either.Left(ErrorModel(response.code().toString() , response.message()))
                        }
                        else -> {
                            Either.Left(getGenericError())
                        }
                    }
                }
            } ?: apply {
                return when (val code = response.code()) {
                    HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        Either.Left(ErrorModel(code.toString(), "Session expired!"))
                    }
                    HttpURLConnection.HTTP_GONE -> {
                        Either.Left(ErrorModel(code.toString(), response.message()))
                    }
                    else -> Either.Left(getGenericError())
                }
            }
        } catch (e: Exception) {
            return Either.Left(getGenericError())
        } catch (e: Throwable) {
            return Either.Left(getGenericError())
        }
        return Either.Left(getGenericError())
    }

    protected fun getGenericError() = ErrorModel(
        "400",
        "Something went wrong!"
    )

    protected fun getNoNetworkError() = ErrorModel(
        AppConstants.INetworkError.NO_NETWORK,
        "No network!"
    )
}