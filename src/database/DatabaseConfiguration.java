package database;

public class DatabaseConfiguration {
    private final String _address;
    private final String _port;
    private final String _username;
    private final String _password;
    private final String _databaseName;

    public String GetPassword() {
        return _password;
    }

    public String GetUsername() {
        return _username;
    }

    public String GetPort() {
        return _port;
    }

    public String GetAddress() {
        return _address;
    }

    public String GetDatabaseName() {
        return _databaseName;
    }

    public String GetConnectionString() {
        return "jdbc:mysql://" + _address + ":" + _port + "/" + _databaseName;
    }

    public DatabaseConfiguration(String address, String port, String username, String password, String databaseName) {
        this._address = address;
        this._port = port;
        this._username = username;
        this._password = password;
        this._databaseName = databaseName;
    }

    @Override
    public String toString() {
        return "{" + " _address='" + GetAddress() + "'" + ", _port='" + GetPort() + "'" + ", _username='"
                + GetUsername() + "'" + ", _password='" + GetPassword() + "'" + "}";
    }

}
