package `in`.socialninja.bloodplus.utils

import androidx.lifecycle.MutableLiveData

/**
 * Created by ayushshrivastava on 25/09/20.
 */
object DataUtils {

    val userName: MutableLiveData<String> = MutableLiveData()
    val userEmail: MutableLiveData<String> = MutableLiveData()
    val userDob: MutableLiveData<String> = MutableLiveData()
    val userMobile: MutableLiveData<String> = MutableLiveData()
    val userGender: MutableLiveData<String> = MutableLiveData()

}