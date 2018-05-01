package amalhanaja.github.io.introductionrestapi.service

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.kotlin.createRetrofitService

/**
 * Created by Alfian Akmal Hanantio on 01/05/18.
 * Email : amalhanaja@gmail.com
 * Github: https://github.com/amalhanaja/
 */
interface UserService {

    companion object {
        fun create(): UserService {
            return createRetrofitService<UserService> {
                baseUrl = "http://192.168.1.15:8001"
                client = OkHttpClient.get()
                converterFactories = arrayListOf(MoshiConverterFactory.create(Moshi.Builder().build()))
                callAdapterFactories = arrayListOf(RxJava2CallAdapterFactory.create())
            }
        }
    }

    @PUT("/register")
    fun register(@Body params: Params.Register): Observable<retrofit2.Response<Response.Token>>

    object Params {
        data class Register(

                @Json(name="password")
                val password: String? = null,

                @Json(name="fullName")
                val fullName: String? = null,

                @Json(name="email")
                val email: String? = null)
    }

    object Response {
        data class Token (

                @Json(name="token")
                val token: String? = null
        )
    }
}