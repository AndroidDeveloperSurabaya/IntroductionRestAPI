package amalhanaja.github.io.introductionrestapi.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Alfian Akmal Hanantio on 01/05/18.
 * Email : amalhanaja@gmail.com
 * Github: https://github.com/amalhanaja/
 */
object OkHttpClient {
    fun get(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
    }
}