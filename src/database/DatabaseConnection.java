package database;

import java.sql.*;

public class DatabaseConnection implements IDatabaseConnection {

    private final DatabaseConfiguration _configuration;
    private Connection _connection;

    public Connection GetConnection() {
        return _connection;
    }

    public DatabaseConnection(DatabaseConfiguration _configuration) {
        this._configuration = _configuration;
        this._connection = null;
    }

    @Override
    public void OpenConnection() throws SQLException {
        if (_connection != null)
            return;
        this._connection = DriverManager.getConnection(_configuration.GetConnectionString(),
                _configuration.GetUsername(), _configuration.GetPassword());
    }

    @Override
    public void CloseConnection() throws SQLException {
        if (_connection == null)
            return;
        this._connection.close();
        this._connection = null;
    }

}
