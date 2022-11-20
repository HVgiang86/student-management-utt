package vn.edu.utt.uttqlsv.controller

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import vn.edu.utt.uttqlsv.view.activities.StudentList

class LoginController {
    fun login(context:Context, username:String, password:String) {
        if (LocalAccountManager.isLoginValid(username,password)) {
            Toast.makeText(context,"Login successfully!",Toast.LENGTH_SHORT).show()
            val intent = Intent(context,StudentList::class.java)
            context.startActivity(intent)
        }
        Toast.makeText(context,"Login fail! Password or Username incorrect!",Toast.LENGTH_SHORT).show()
    }
}