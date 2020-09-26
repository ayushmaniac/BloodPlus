package `in`.socialninja.bloodplus.activity

import `in`.socialninja.bloodplus.R
import `in`.socialninja.bloodplus.httphandler.SHPref
import `in`.socialninja.bloodplus.utils.DataUtils
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_complete_profile.*

class CompleteProfileActivity : AppCompatActivity(), View.OnClickListener {

    var gender: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_profile)
        fetchUserData()
        initParallelOperations()
    }

    private fun initParallelOperations() {
        btnSave?.setOnClickListener(this)
        sGroup?.setOnCheckedChangeListener { radioGroup, i ->
            val radio = findViewById<RadioButton>(i)
            gender = radio.text.toString()

        }
    }

    private fun fetchUserData() {
        val mobile = SHPref.getDefaults("password", applicationContext)
        if (mobile.isNotEmpty()) {
            mobileNo?.setText(mobile)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnSave -> {
                saveFieldsAndThrowNetworkCalls()
            }
        }
    }

    private fun saveFieldsAndThrowNetworkCalls() {

        val name = name?.text.toString()
        val email = edEmail?.text.toString()
        val dob = edDob?.text.toString()
        val mobile = mobileNo?.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty() && dob.isNotEmpty() && mobile.isNotEmpty() && gender.isNotEmpty()) {
            DataUtils.userName.postValue(name)
            DataUtils.userDob.postValue(dob)
            DataUtils.userEmail.postValue(email)
            DataUtils.userGender.postValue(gender)
            DataUtils.userMobile.postValue(SHPref.getDefaults("password", this))
            val intent = Intent(this, CompleteProfileSecond::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_LONG).show()
        }

    }


}