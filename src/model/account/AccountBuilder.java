package model.account;

public class AccountBuilder {
    private Integer _id;
    private String _firstName;
    private String _lastName;
    private String _email;
    private String _password;
    private AccountTypeEnum _type;

    public AccountBuilder SetId(Integer id) {
        this._id = id;
        return this;
    }

    public AccountBuilder SetFirstName(String firstName) {
        this._firstName = firstName;
        return this;
    }

    public AccountBuilder SetLastName(String lastName) {
        this._lastName = lastName;
        return this;
    }

    public AccountBuilder SetEmail(String email) {
        this._email = email.toLowerCase();
        return this;
    }

    public AccountBuilder SetPassword(String password) {
        this._password = password;
        return this;
    }

    public AccountBuilder SetType(AccountTypeEnum type) {
        this._type = type;
        return this;
    }

    public Account Build() {
        return new Account(_id, _firstName, _lastName, _email, _password, _type);
    }
}
