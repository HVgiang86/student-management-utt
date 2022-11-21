package vn.edu.utt.uttqlsv.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            if (isVisiblePassword)
                show_password_btn.setImageResource(R.drawable.ic_visibility_off)
            else
                show_password_btn.setImageResource(R.drawable.ic_visibility)
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