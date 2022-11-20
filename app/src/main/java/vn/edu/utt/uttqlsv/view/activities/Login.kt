package vn.edu.utt.uttqlsv.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import vn.edu.utt.uttqlsv.R
import vn.edu.utt.uttqlsv.controller.LoginController

class Login : AppCompatActivity() {
    private var isVisiablePassword = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        show_password_btn.setOnClickListener{
            if (isVisiablePassword)
                show_password_btn.setImageResource(R.drawable.ic_visibility_off)
            else
                show_password_btn.setImageResource(R.drawable.ic_visibility)
        }

        login_btn.setOnClickListener{
            val username = username_edt.text.toString().trim()
            val password = password_edt.text.toString().trim()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                LoginController().login(context = this,username,password)
            }

        }
    }
}