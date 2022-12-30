package vn.edu.utt.uttqlsv.controller;

import android.content.Context;
import android.widget.Toast;

import vn.edu.utt.uttqlsv.controller.utils.Validator;
import vn.edu.utt.uttqlsv.model.Account;

public class SignUpController {


    /**
     * This function will check can register account with input information or not
     * will check username is already exist or not and password is complexity enough and match with re-password field
     *
     * @param username username
     * @param password password
     * @param rePassword reenter password
     * @param email email
     * @param phone phone number
     * @param gender gender, true = male; false = female
     * @param address address
     */
    public static boolean signUp(Context context, String username, String password, String rePassword, String email, String phone, boolean gender, String address) {
        if (LocalAccountManager.getINSTANCE().isUsernameExist(username)) {
            Toast.makeText(context, "username has been taken, please choose another one!", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(context, "Password field must not empty!", Toast.LENGTH_SHORT).show();
        } else if (rePassword.isEmpty()) {
            Toast.makeText(context, "Re-Password field must not empty!", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(rePassword)) {
            Toast.makeText(context, "Re-Password field must equal with password field!", Toast.LENGTH_SHORT).show();
        } else if (!Validator.isValidPassword(password)) {
            Toast.makeText(context, "password is too week, password must contain at least 8 characters with at least 1 uppercase character, 1 special character, 1 digit", Toast.LENGTH_SHORT).show();
        } else {
            Account account = new Account(username, password, email, phone, gender, address);
            LocalAccountManager.getINSTANCE().addAccount(account);
            LocalAccountManager.getINSTANCE().storeAccountList();
            Toast.makeText(context, "Sign up account successfully!", Toast.LENGTH_SHORT).show();

            return true;
        }
        return false;
    }


}
