package vn.edu.utt.uttqlsv.controller

import android.content.Context
import android.widget.Toast
import vn.edu.utt.uttqlsv.model.Account
import vn.edu.utt.uttqlsv.model.Gender

class SignUpController {
    fun signUp(
        context: Context,
        username: String,
        password: String,
        repassword: String,
        email: String,
        phone: String,
        gender: Gender,
        address: String
    ):Boolean {
        if (LocalAccountManager.isUsernameExist(username)) {
            Toast.makeText(context,"username has been taken, please choose another one!",Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(context,"Password field must not empty!",Toast.LENGTH_SHORT).show()
        } else if (repassword.isEmpty()) {
            Toast.makeText(context,"Re-Password field must not empty!",Toast.LENGTH_SHORT).show()
        } else if (password != repassword){
            Toast.makeText(context,"Re-Password field must equal with password field!",Toast.LENGTH_SHORT).show()
        } else {
            val newAccount = Account(username,password,email,phone,gender,address)
            LocalAccountManager.addAccount(newAccount)
            LocalAccountManager.storeAccountList()
            Toast.makeText(context,"Sign up account successfully!",Toast.LENGTH_SHORT).show()
            return true
        }

        return false
    }
}