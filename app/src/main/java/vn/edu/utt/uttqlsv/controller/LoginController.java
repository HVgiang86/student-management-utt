package vn.edu.utt.uttqlsv.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import vn.edu.utt.uttqlsv.view.activities.StudentList;

public class LoginController {

    /**
     * This function will load saved account in internal storage by shared preferences
     */
    public static void loadStoredAccount(Context context) {
        LocalAccountManager.getINSTANCE().getStoredAccount(context);
    }

    /**
     * This function will check if username and password is match with any stored account in local memory
     * @param username username to login
     * @param password password to login
     * @return true if login successfully and false if not
     */
    public static boolean login(Activity activity, String username, String password) {
        if (LocalAccountManager.getINSTANCE().isLoginValid(username, password)) {
            Toast.makeText(activity, "Login successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, StudentList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
            ActivityCompat.finishAffinity(activity);
            return true;
        }

        Toast.makeText(activity, "Login fail! Password or Username incorrect!", Toast.LENGTH_SHORT).show();
        return false;
    }


}
