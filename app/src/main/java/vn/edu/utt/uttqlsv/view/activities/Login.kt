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

        val loginController = LoginController()
        loginController.loadStoredAccount(this)

        show_password_btn.setOnClickListener{
            if (isVisiblePassword) {
                show_password_btn.setImageResource(R.drawable.ic_visibility)
                password_edt.transformationMethod = PasswordTransformationMethod()
                isVisiblePassword = false

            }
            else {
                show_password_btn.setImageResource(R.drawable.ic_visibility_off)
                password_edt.transformationMethod = null
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