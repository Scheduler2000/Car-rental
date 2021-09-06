package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.DatabaseRecord;

public abstract class DatabaseHandler<TEntity> {
    /*
     * Here we can use reflection and make almost a micro orm. But our application
     * is simple enough as to just present data directly from the database or the
     * relationships between them is simple enough. So I conclude that JDBC/SQL is
     * fine and we don't have to make or use an orm/micro orm.
     * 
     * NB : About predicate what we can do here is to implement a filter logic with
     * Predicate lambda (Predicate<TEntity>). In this case we will be able to handle
     * OR/AND logic easily for SELECT statement.
     */

    protected final Connection connection;
    protected final String tableName;

    protected DatabaseHandler(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    public abstract boolean Create(TEntity entity) throws SQLException;

    public abstract boolean Update(TEntity entity) throws SQLException;

    public abstract List<TEntity> GetEntities(TEntity predicate) throws SQLException;

    public boolean Delete(TEntity entity) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        PreparedStatement statement = this.connection.prepareStatement(sql);

        statement.setInt(1, ((DatabaseRecord) entity).GetId());

        return statement.executeUpdate() > 0;
    }

    public TEntity GetEntity(TEntity predicate) throws SQLException {
        var entities = GetEntities(predicate);

        return entities.isEmpty() ? null : entities.get(0);
    }

}
