package vn.edu.utt.uttqlsv.controller

import vn.edu.utt.uttqlsv.model.Account

object LocalAccountManager {
    private var accountList = mutableListOf<Account>()

    fun isUsernameExist(username: String): Boolean {
        for(account in accountList) {
            if (account.username.equals(username)) {
                return true
            }
        }
        return false
    }

    fun isLoginValid(username: String, password:String): Boolean {
        for (account in accountList) {
            if (account.username.equals(username) && account.hashPassword.equals(password)) {
                return true
            }
        }
        return false
    }

    fun addAccount(account: Account) {
        accountList.add(account)
    }
}