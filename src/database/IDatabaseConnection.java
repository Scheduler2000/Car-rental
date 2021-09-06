package database;

import java.sql.SQLException;

public interface IDatabaseConnection {

    public void OpenConnection() throws SQLException;

    public void CloseConnection() throws SQLException;

}
