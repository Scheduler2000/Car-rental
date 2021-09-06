package database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import model.account.Account;
import model.account.AccountBuilder;
import model.account.AccountTypeEnum;

public class AccountRepository extends DatabaseHandler<model.account.Account> {

    public AccountRepository(Connection connection) {
        super(connection, "Account");
    }

    @Override
    public boolean Create(Account entity) throws SQLException {

        if (super.GetEntity(new AccountBuilder().SetEmail(entity.GetEmail()).Build()) != null)
            return false;

        String sql = "INSERT INTO Account (first_name, last_name, email, password, type) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setString(1, entity.GetFirstName());
        statement.setString(2, entity.GetLastName());
        statement.setString(3, entity.GetEmail());
        statement.setString(4, entity.GetPassword());
        statement.setByte(5, entity.GetType() == AccountTypeEnum.Employee ? (byte) 0 : (byte) 1);

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean Update(Account entity) throws SQLException {
        String sql = "UPDATE Account SET first_name=?, last_name=?, password=? WHERE id=?";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setString(1, entity.GetFirstName());
        statement.setString(2, entity.GetLastName());
        statement.setString(3, entity.GetPassword());
        statement.setInt(4, entity.GetId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public List<Account> GetEntities(Account predicate) throws SQLException {
        var accountBuilder = new AccountBuilder();
        var accounts = new ArrayList<Account>();
        String sql = "";

        if (predicate == null) {
            sql = "SELECT * FROM account";
        } else {
            var query = new StringBuilder();
            query.append("SELECT * FROM Account WHERE ");

            if (predicate.GetFirstName() != null)
                query.append("first_name='" + predicate.GetFirstName() + "' AND ");
            if (predicate.GetLastName() != null)
                query.append("last_name='" + predicate.GetLastName() + "' AND ");
            if (predicate.GetEmail() != null)
                query.append("email='" + predicate.GetEmail() + "' AND ");
            if (predicate.GetPassword() != null)
                query.append("password='" + predicate.GetPassword() + "' AND ");

            query.delete(query.length() - 5, query.length()); /* remove AND */

            sql = query.toString();
        }

        Statement statement = super.connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            accountBuilder.SetId(rs.getInt("id")).SetFirstName(rs.getString("first_name"))
                    .SetLastName(rs.getString("last_name")).SetEmail(rs.getString("email"))
                    .SetPassword(rs.getString("password"))
                    .SetType(rs.getByte("type") == 0 ? AccountTypeEnum.Employee : AccountTypeEnum.Customer);

            accounts.add(accountBuilder.Build());
        }

        return accounts;
    }

}
