package vn.edu.utt.uttqlsv.controller.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONObject
import vn.edu.utt.uttqlsv.model.Account
import vn.edu.utt.uttqlsv.model.Gender

object LocalAccountLoader {
    private const val PREFS_FILE = "accounts_storage_file"
    private const val PREFS_MODE = Context.MODE_PRIVATE
    private const val USER_AMOUNT_KEY = "USER_AMOUNT_KEY"
    private const val USER_DETAIL_KEY = "USER_DETAIL_KEY"
    private const val USERNAME_KEY = "username"
    private const val PASSWORD_KEY = "password"
    private const val EMAIL_KEY = "email"
    private const val PHONE_NUMBER_KEY = "phone_number"
    private const val GENDER_KEY = "gender"
    private const val ADDRESS_KEY = "address"

    private lateinit var preferences: SharedPreferences

    fun openPreferenceMode(context: Context) {
        preferences = context.getSharedPreferences(PREFS_FILE, PREFS_MODE)
    }

    fun getStoredAccountList(): MutableList<Account> {
        val numberOfAccount: Int = preferences.getInt(USER_AMOUNT_KEY,0)
        val accountList = mutableListOf<Account>()

        if (numberOfAccount != 0) {
            Log.d("ACCOUNT TAG","$numberOfAccount accounts stored!")
            for (i in 0.until(numberOfAccount)) {
                val stringJson = preferences.getString(USER_DETAIL_KEY+i,"")
                Log.d("JSON TAG", stringJson!!)
                val jsonObject = JSONObject(stringJson!!)
                val username = jsonObject.getString(USERNAME_KEY)
                val password = jsonObject.getString(PASSWORD_KEY)
                val email = jsonObject.getString(EMAIL_KEY)
                val phoneNumber = jsonObject.getString(PHONE_NUMBER_KEY)
                val genderString = jsonObject.getString(GENDER_KEY)
                val address = jsonObject.getString(ADDRESS_KEY)

                val gender: Gender = if ("male".equals(other = genderString, ignoreCase = true))
                    Gender.Male
                else
                    Gender.Female

                Log.d("ACCOUNT TAG","account: username:$username; password:$password; email: $email; phone: $phoneNumber; gender: $genderString; address: $address")
                accountList.add(Account(username,password,email,phoneNumber,gender,address))
            }
        }

        return accountList
    }

    fun storeAccountList(accountList: MutableList<Account>) {
        val editor = preferences.edit()
        editor.putInt(USER_AMOUNT_KEY, accountList.size)

        for (i in 0.until(accountList.size)) {
            val jsonObject = JSONObject()
            val account = accountList[i]

            jsonObject.put(USERNAME_KEY,account.username)
            jsonObject.put(PASSWORD_KEY,account.hashPassword)
            jsonObject.put(EMAIL_KEY,account.email)
            jsonObject.put(PHONE_NUMBER_KEY,account.phoneNumber)
            if (account.gender == Gender.Male)
                jsonObject.put(GENDER_KEY,"male")
            else
                jsonObject.put(GENDER_KEY,"female")

            jsonObject.put(ADDRESS_KEY,account.address)

            editor.putString(USER_DETAIL_KEY+i,jsonObject.toString())
        }

        editor.apply()
    }
}