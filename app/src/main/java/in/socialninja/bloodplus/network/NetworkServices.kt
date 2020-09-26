package `in`.socialninja.bloodplus.network

import `in`.socialninja.bloodplus.data.UserCompletion
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by ayushshrivastava on 25/09/20.
 */
interface NetworkServices {

    @POST("users/check")
    fun getUserCompletion(@Query("mobile") mobile: String): Call<UserCompletion>

}