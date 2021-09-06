package database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import model.invoice.Invoice;
import model.invoice.InvoiceBuilder;

public class InvoiceRepository extends DatabaseHandler<Invoice> {

    public InvoiceRepository(Connection connection) {
        super(connection, "Invoice");
    }

    @Override
    public boolean Create(Invoice entity) throws SQLException {
        String sql = "INSERT INTO Invoice (account_id, booking_id) VALUES (?, ?)";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setInt(1, entity.GetAccountId());
        statement.setInt(2, entity.GetBookingId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean Delete(Invoice entity) throws SQLException {
        String sql = "DELETE FROM Invoice WHERE id=?";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setInt(1, entity.GetId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean Update(Invoice entity) throws SQLException {
        return false;
    }

    @Override
    public List<Invoice> GetEntities(Invoice predicate) throws SQLException {
        var invoiceBuilder = new InvoiceBuilder();
        var invoices = new ArrayList<Invoice>();
        String sql = "";

        if (predicate == null) {
            sql = "SELECT * FROM Invoice";
        } else {
            var query = new StringBuilder();
            query.append("SELECT * FROM Invoice WHERE ");

            if (predicate.GetAccountId() != null)
                query.append("account_id='" + predicate.GetAccountId() + "' AND ");
            if (predicate.GetBookingId() != null)
                query.append("booking_id='" + predicate.GetBookingId() + "' AND ");

            query.delete(query.length() - 5, query.length()); /* remove AND */

            sql = query.toString();
        }

        Statement statement = super.connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            invoiceBuilder.SetId(rs.getInt("id")).SetAccountId(rs.getInt("account_id"))
                    .SetBookingId(rs.getInt("booking_id"));

            invoices.add(invoiceBuilder.Build());
        }

        return invoices;
    }

}