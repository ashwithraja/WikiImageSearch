package com.mahadream.wikiimagesearch.data.remote

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


open class WikiNetworkClient {

    companion object {
        const val BASE_URL = "https://en.wikipedia.org/"
    }

    private val okHttpClient by lazy {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
         okHttpClientBuilder.addInterceptor((LoggInterceptor()))
        okHttpClientBuilder.addInterceptor(CommonHeaderInterceptor())
        okHttpClientBuilder.build()
    }

    class CommonHeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val requestOriginal = chain.request()
            val originalHttpUrl: HttpUrl = requestOriginal.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("action", "query")
                .addQueryParameter("prop","pageimages")
                .addQueryParameter("format","json")
                .addQueryParameter("piprop","thumbnail")
                .addQueryParameter("generator","prefixsearch")
                .addQueryParameter("pithumbsize","2000")
                .build()


            val requestBuilder: Request.Builder = requestOriginal.newBuilder()
                .url(url)

           val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }

    fun create(): WikiImageApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WikiImageApiService::class.java)
    }
}