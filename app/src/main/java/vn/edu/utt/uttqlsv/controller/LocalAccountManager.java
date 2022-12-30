package vn.edu.utt.uttqlsv.controller;

import android.content.Context;

import java.util.ArrayList;

import vn.edu.utt.uttqlsv.controller.utils.LocalAccountLoader;
import vn.edu.utt.uttqlsv.model.Account;

public class LocalAccountManager {
    //single ton installation
    private static final LocalAccountManager INSTANCE = new LocalAccountManager();
    private ArrayList<Account> accountList = new ArrayList<>();

    private LocalAccountManager() {
    }

    public static LocalAccountManager getINSTANCE() {
        return INSTANCE;
    }
    //end of single ton installation

    public boolean isUsernameExist(String username) {
        for (Account a : accountList) {
            if (a.getUsername().equals(username)) return true;
        }
        return false;
    }

    public boolean isLoginValid(String username, String password) {
        for (Account a : accountList) {
            if (a.getUsername().equals(username) && a.getHashPassword().equals(password))
                return true;
        }
        return false;
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public void getStoredAccount(Context context) {
        LocalAccountLoader.getInstance().openPreferenceMode(context);
        accountList = LocalAccountLoader.getInstance().getStoredAccountList();
    }

    public void storeAccountList() {
        LocalAccountLoader.getInstance().storeAccountList(accountList);
    }

}
