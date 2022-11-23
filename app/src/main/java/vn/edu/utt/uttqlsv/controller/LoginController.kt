package vn.edu.utt.uttqlsv.controller

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.ActivityCompat
import vn.edu.utt.uttqlsv.view.activities.StudentList

class LoginController {

    /**
     * This function will load saved account in internal storage by shared preferences
     */
    fun loadStoredAccount(context: Context) {
        LocalAccountManager.getStoredAccount(context)
    }

    /**
     * This function will check if username and password is match with any stored account in local memory
     * @param username
     * @param password
     * @return true if login successfully and false if not
     */
    fun login(activity: Activity, username: String, password: String): Boolean {

        if (LocalAccountManager.isLoginValid(username, password)) {
            Toast.makeText(activity, "Login successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, StudentList::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            activity.startActivity(intent)
            ActivityCompat.finishAffinity(activity)
            return true
        } else {
            Toast.makeText(
                activity, "Login fail! Password or Username incorrect!", Toast.LENGTH_SHORT
            ).show()
        }
        return false
    }
}