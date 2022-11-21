package vn.edu.utt.uttqlsv.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.LoginController

class Login : AppCompatActivity() {
    private var isVisiblePassword = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginController = LoginController()
        loginController.loadStoredAccount(this)

        show_password_btn.setOnClickListener{
            if (isVisiblePassword) {
                show_password_btn.setImageResource(R.drawable.ic_visibility_off)
                password_edt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                isVisiblePassword = false

            }
            else {
                show_password_btn.setImageResource(R.drawable.ic_visibility)
                password_edt.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                isVisiblePassword = true
            }

        }

        login_btn.setOnClickListener{
            val username = username_edt.text.toString().trim()
            val password = password_edt.text.toString().trim()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                loginController.login(context = this,username,password)
            }
        }

        sign_up_text_btn.setOnClickListener{
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
    }
}