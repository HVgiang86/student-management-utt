package vn.edu.utt.uttqlsv.controller

import android.content.Context
import vn.edu.utt.uttqlsv.controller.utils.LocalAccountLoader
import vn.edu.utt.uttqlsv.model.Account

object LocalAccountManager {
    private var accountList = mutableListOf<Account>()

    fun isUsernameExist(username: String): Boolean {
        for (account in accountList) {
            if (account.username == username) {
                return true
            }
        }
        return false
    }

    fun isLoginValid(username: String, password: String): Boolean {
        for (account in accountList) {
            if (account.username == username && account.hashPassword == password) {
                return true
            }
        }
        return false
    }

    fun addAccount(account: Account) {
        accountList.add(account)
    }

    fun getStoredAccount(context: Context) {
        LocalAccountLoader.openPreferenceMode(context)

        accountList = LocalAccountLoader.getStoredAccountList()
    }

    fun storeAccountList() {
        LocalAccountLoader.storeAccountList(accountList)

    }
}