package vn.edu.utt.uttqlsv.controller.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.utt.uttqlsv.model.Account;
import vn.edu.utt.uttqlsv.model.Gender;

public class LocalAccountLoader {
    private static final String TAG = "ACCOUNT TAG";
    private static final String PREFS_FILE = "accounts_storage_file";
    private static final int PREFS_MODE = Context.MODE_PRIVATE;
    private static final String USER_AMOUNT_KEY = "USER_AMOUNT_KEY";
    private static final String USER_DETAIL_KEY = "USER_DETAIL_KEY";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String EMAIL_KEY = "email";
    private static final String PHONE_NUMBER_KEY = "phone_number";
    private static final String GENDER_KEY = "gender";
    private static final String ADDRESS_KEY = "address";
    //singleton pattern installation
    private static final LocalAccountLoader INSTANCE = new LocalAccountLoader();
    private SharedPreferences preferences;

    private LocalAccountLoader() {
    }

    public static LocalAccountLoader getInstance() {
        return INSTANCE;
    }//end of installation

    public void openPreferenceMode(Context context) {
        preferences = context.getSharedPreferences(PREFS_FILE, PREFS_MODE);
    }

    public ArrayList<Account> getStoredAccountList() {
        int numberOfAccount = preferences.getInt(USER_AMOUNT_KEY, 0);
        ArrayList<Account> accountList = new ArrayList<>();

        if (numberOfAccount != 0) {
            Log.d(TAG, "number of accounts: " + numberOfAccount);
            for (int i = 0; i < numberOfAccount; ++i) {
                String stringJson = preferences.getString(USER_DETAIL_KEY + i, "");
                Log.d(TAG, stringJson);

                if (stringJson.length() != 0) {
                    try {
                        JSONObject jsonObject = new JSONObject(stringJson);
                        String username = jsonObject.getString(USERNAME_KEY);
                        String password = jsonObject.getString(PASSWORD_KEY);
                        String email = jsonObject.getString(EMAIL_KEY);
                        String phoneNumber = jsonObject.getString(PHONE_NUMBER_KEY);
                        String genderString = jsonObject.getString(GENDER_KEY);
                        String address = jsonObject.getString(ADDRESS_KEY);

                        boolean gender;
                        if ("male".equalsIgnoreCase(genderString)) gender = Gender.MALE;
                        else gender = Gender.FEMALE;

                        Account newAccount = new Account(username, password, email, phoneNumber, gender, address);
                        accountList.add(newAccount);
                        Log.d(TAG, "account: " + newAccount);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return accountList;
    }

    public void storeAccountList(ArrayList<Account> accountList) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(USER_AMOUNT_KEY, accountList.size());

        for (Account a : accountList) {
            JSONObject jsonObject = new JSONObject();
            int index = accountList.indexOf(a);

            try {
                jsonObject.put(USERNAME_KEY, a.getUsername());
                jsonObject.put(PASSWORD_KEY, a.getHashPassword());
                jsonObject.put(EMAIL_KEY, a.getEmail());
                jsonObject.put(PHONE_NUMBER_KEY, a.getPhoneNumber());
                if (a.isGender() == Gender.MALE) jsonObject.put(GENDER_KEY, "male");
                else jsonObject.put(GENDER_KEY, "female");

                jsonObject.put(ADDRESS_KEY, a.getAddress());

                editor.putString(USER_DETAIL_KEY + index, jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        editor.apply();
    }

}
