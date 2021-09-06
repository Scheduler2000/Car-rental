package database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import model.booking.Booking;
import model.booking.BookingBuilder;

public class BookingRepository extends DatabaseHandler<Booking> {

    public BookingRepository(Connection connection) {
        super(connection, "Booking");
    }

    @Override
    public boolean Create(Booking entity) throws SQLException {
        String sql = "INSERT INTO Booking (car_id, customer_id, pick_up_date, return_date) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setInt(1, entity.GetCarId());
        statement.setInt(2, entity.GetCustomerId());
        statement.setDate(3, new java.sql.Date(entity.GetPickUpDate().getTime()));
        statement.setDate(4, new java.sql.Date(entity.GetReturnDate().getTime()));

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean Update(Booking entity) throws SQLException {
        String sql = "UPDATE Booking SET pick_up=?, return=? WHERE id=?";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setDate(1, new java.sql.Date(entity.GetPickUpDate().getTime()));
        statement.setDate(2, new java.sql.Date(entity.GetReturnDate().getTime()));
        statement.setInt(3, entity.GetId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public List<Booking> GetEntities(Booking predicate) throws SQLException {
        var bookingBuilder = new BookingBuilder();
        var bookings = new ArrayList<Booking>();
        String sql = "";

        if (predicate == null) {
            sql = "SELECT * FROM Booking";
        } else {
            var query = new StringBuilder();
            query.append("SELECT * FROM Booking WHERE ");

            if (predicate.GetCarId() != null)
                query.append("car_id='" + predicate.GetCarId() + "' AND ");
            if (predicate.GetCustomerId() != null)
                query.append("customer_id='" + predicate.GetCustomerId() + "' AND ");

            query.delete(query.length() - 5, query.length()); /* remove AND */

            sql = query.toString();
        }

        Statement statement = super.connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            bookingBuilder.SetId(rs.getInt("id")).SetCarId(rs.getInt("car_id")).SetCustomerId(rs.getInt("customer_id"))
                    .SetPickUpDate(rs.getDate("pick_up_date")).SetReturnDate(rs.getDate("return_date"));

            bookings.add(bookingBuilder.Build());
        }

        return bookings;
    }

}
