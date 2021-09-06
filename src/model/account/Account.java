package model.account;

import model.DatabaseRecord;

public class Account extends DatabaseRecord {
    private final String _firstName;
    private final String _lastName;
    private final String _email;
    private final String _password;
    private final AccountTypeEnum _type;

    public String GetFirstName() {
        return _firstName;
    }

    public String GetLastName() {
        return _lastName;
    }

    public String GetEmail() {
        return _email;
    }

    public String GetPassword() {
        return _password;
    }

    public AccountTypeEnum GetType() {
        return _type;
    }

    public Account(Integer id, String firstName, String lastName, String email, String password, AccountTypeEnum type) {
        super(id);
        this._firstName = firstName;
        this._lastName = lastName;
        this._email = email;
        this._password = password;
        this._type = type;
    }

    @Override
    public String toString() {
        return "Account : {\n\tid=" + GetId() + "," + "\n\tfirstname=" + _firstName + "," + "\n\tlastname=" + _lastName
                + "," + "\n\temail=" + _email + "," + "\n\tpassword=" + _password + "," + "\n\ttype=" + _type + "\n}";
    }
}
