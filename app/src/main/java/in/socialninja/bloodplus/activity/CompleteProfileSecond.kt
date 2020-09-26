package `in`.socialninja.bloodplus.activity

import `in`.socialninja.bloodplus.R
import `in`.socialninja.bloodplus.adapters.DateDialog
import `in`.socialninja.bloodplus.httphandler.HttpClientActivity
import `in`.socialninja.bloodplus.httphandler.InternetConnection
import `in`.socialninja.bloodplus.utils.DataUtils
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_complete_profile_second.*
import org.json.JSONObject
import java.util.*

class CompleteProfileSecond : AppCompatActivity(), View.OnClickListener {

    var medicalProblemString = ""
    private var donorBloodGroup: String? = null
    private var userName = ""
    private var mobileNumber = ""
    private var userDob = ""
    private var userEmail = ""
    private var userGender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_profile_second)
        initParallelOperations()
    }

    private fun initParallelOperations() {
        setOnClickListeners()
        setSpinnerData()
        setRadioGroup()
        observeInputs()
    }

    private fun observeInputs() {
        DataUtils.userGender.observe(this, {
            it?.let {
                userGender = it
            }
        })
        DataUtils.userDob.observe(this, {
            it?.let {
                userDob = it
            }

        })
        DataUtils.userDob.observe(this, {
            it?.let {
                userDob = it
            }

        })
        DataUtils.userName.observe(this, {
            it?.let {
                userName = it
            }
        })

        DataUtils.userMobile.observe(this, {
            it?.let {
                mobileNumber = it
            }

        })
    }


    private fun setRadioGroup() {
        medicationgroup?.setOnCheckedChangeListener { radioGroup, i ->
            val radio: RadioButton = findViewById(i)
            medicalProblemString = radio.text.toString()
        }
    }

    private fun setSpinnerData() {
        val spinner = findViewById<View>(R.id.city_spinner) as Spinner
        val adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(Objects.requireNonNull(applicationContext), R.array.drop_city, R.layout.spinner_item)
        spinner.adapter = adapter
    }

    private fun setOnClickListeners() {
        a_plus?.setOnClickListener(this)
        b_plus?.setOnClickListener(this)
        ab_plus?.setOnClickListener(this)
        o_plus?.setOnClickListener(this)
        a_minus?.setOnClickListener(this)
        b_minus?.setOnClickListener(this)
        ab_minus?.setOnClickListener(this)
        o_minus?.setOnClickListener(this)
        reg_btn_submit?.setOnClickListener(this)
        txtDatepic?.setOnClickListener(this)
    }

    private fun checkAndThrowCalls() {
        val selectedCity = city_spinner.selectedItem.toString()
        val lastDonationDate = txtDatepic.text.toString()
        if (selectedCity.isNotEmpty() && lastDonationDate.isNotEmpty()) {
            val params = RequestParams()
            params.put("bu_name", userName)
            params.put("bu_contact", mobileNumber)
            params.put("bu_email", userEmail)
            params.put("bu_dob", userDob)
            params.put("bu_gender", userGender)
            params.put("bu_b_group", donorBloodGroup)
            params.put("bu_d_date", lastDonationDate)
            params.put("bu_m_problem", medicalProblemString)
            params.put("bu_city", selectedCity)
            if (InternetConnection.checkConnection(Objects.requireNonNull(applicationContext))) {
                HttpClientActivity.post("users/", params, object : JsonHttpResponseHandler() {
                    override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {

                    }

                    override fun onFailure(statusCode: Int, headers: Array<Header>, responseString: String, throwable: Throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable)
                        if (statusCode <= 400) {
                            Toast.makeText(this@CompleteProfileSecond, "Bad Request or Server not available", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@CompleteProfileSecond, "Something went wrong please check your connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            } else {
                Toast.makeText(this, "Please check your connection", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.txtDatepic -> {
                val dfrag: DialogFragment = DateDialog(view)
                dfrag.show(supportFragmentManager, "DateFragment ")
            }
            R.id.reg_btn_submit -> {
                checkAndThrowCalls()
            }
            R.id.a_plus -> {
                a_plus?.setBackgroundResource(R.drawable.roundshapered)
                b_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                a_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                donorBloodGroup = "A[plus]"

            }
            R.id.b_plus -> {
                a_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_plus?.setBackgroundResource(R.drawable.roundshapered)
                ab_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                a_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                donorBloodGroup = "B[plus]"

            }
            R.id.ab_plus -> {
                a_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_plus?.setBackgroundResource(R.drawable.roundshapered)
                o_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                a_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                donorBloodGroup = "AB[plus]"

            }

            R.id.o_plus -> {
                a_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_plus?.setBackgroundResource(R.drawable.roundshapered)
                a_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                donorBloodGroup = "O[plus]"

            }
            R.id.a_minus -> {
                a_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                a_minus?.setBackgroundResource(R.drawable.roundshapered)
                b_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                donorBloodGroup = "A-"

            }
            R.id.b_minus -> {
                a_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                a_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_minus?.setBackgroundResource(R.drawable.roundshapered)
                ab_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                donorBloodGroup = "B-"

            }
            R.id.ab_minus -> {
                a_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                a_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_minus?.setBackgroundResource(R.drawable.roundshapered)
                o_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                donorBloodGroup = "AB-"

            }
            R.id.o_minus -> {
                a_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_plus?.setBackgroundResource(R.drawable.blood_group_btn)
                a_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                b_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                ab_minus?.setBackgroundResource(R.drawable.blood_group_btn)
                o_minus?.setBackgroundResource(R.drawable.roundshapered)
                donorBloodGroup = "O-"

            }


        }
    }
}