package vn.edu.utt.uttqlsv.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.LoginController


class Login : AppCompatActivity() {
    private var isVisiblePassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Load saved account from Shared Preferences
        val loginController = LoginController()
        loginController.loadStoredAccount(this)

        //Handle show password button
        //switch between visible and invisible password
        login_in_activity_show_password_btn.setOnClickListener {
            if (isVisiblePassword) setPasswordInvisible()
            else setPasswordVisible()
        }

        //Handle Login button
        login_btn.setOnClickListener {
            val username = login_activity_username_edt.text.toString().trim()
            val password = login_activity_password_edt.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (loginController.login(this, username, password)) {
                    login_activity_password_edt.setText("")
                }
            }
        }

        login_activity_sign_up_text_btn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun setPasswordVisible() {
        login_in_activity_show_password_btn.setImageResource(R.drawable.ic_visibility_off)
        login_activity_password_edt.transformationMethod = null
        isVisiblePassword = true
    }

    private fun setPasswordInvisible() {
        login_in_activity_show_password_btn.setImageResource(R.drawable.ic_visibility)
        login_activity_password_edt.transformationMethod = PasswordTransformationMethod()
        isVisiblePassword = false
    }
}