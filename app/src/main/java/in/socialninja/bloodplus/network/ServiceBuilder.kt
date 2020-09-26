package `in`.socialninja.bloodplus.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ayushshrivastava on 25/09/20.
 */
object ServiceBuilder {

    private val BASE_URL = "http://socialninja.in/bloodapi/v1/"

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}