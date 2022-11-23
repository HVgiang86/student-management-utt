package vn.edu.utt.uttqlsv.view.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.SignUpController
import vn.edu.utt.uttqlsv.model.Gender

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //Init gender picker dropdown menu
        initGenderPickerMenu()

        //Handle sign up btn
        //get string from all edit text field then request for signing up
        sign_up_btn.setOnClickListener {
            signUpRequest()
        }
    }

    private fun initGenderPickerMenu() {
        val genderDropDownListItems = arrayOf("Male", "Female")
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, genderDropDownListItems)
        gender_spinner.adapter = adapter
    }

    private fun signUpRequest() {
        //Get all string from edit text field
        val username = username_edt.text.toString().trim()
        val password = password_edt.text.toString().trim()
        val rePassword = re_password_edt.text.toString().trim()
        val email = email_edt.text.toString().trim()
        val phone = phone_edt.text.toString().trim()
        val address = address_edt.text.toString().trim()
        val gender: Boolean
        val i: Long = 0
        gender = if (gender_spinner.selectedItemId == i) Gender.MALE
        else Gender.FEMALE

        //request for register
        if (SignUpController().signUp(
                this, username, password, rePassword, email, phone, gender, address
            )
        ) {
            finish()
        }
    }
}