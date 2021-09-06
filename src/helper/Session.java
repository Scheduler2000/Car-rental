package helper;

import model.account.Account;

public class Session {
    private static Account _account;

    public static Account GetAccount() {
        return _account;
    }

    public static void Initiliaze(Account account) {
        _account = account;
    }

    public static void Clear() {
        _account = null;
    }
}
