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

        val genderDropDownListItems = arrayOf("Male","Female")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,genderDropDownListItems)
        gender_spinner.adapter = adapter

        sign_up_btn.setOnClickListener {
            val username = username_edt.text.toString().trim()
            val password = password_edt.text.toString().trim()
            val repassword = re_password_edt.text.toString().trim()
            val email = email_edt.text.toString().trim()
            val phone = phone_edt.text.toString().trim()
            val address = address_edt.text.toString().trim()
            val gender:Gender
            val i:Long = 0
            gender = if(gender_spinner.selectedItemId == i)
                Gender.Male
            else
                Gender.Female

            if (SignUpController().signUp(
                    this,
                    username,
                    password,
                    repassword,
                    email,
                    phone,
                    gender,
                    address
                )
            ) {
                finish()
            }
        }
    }
}