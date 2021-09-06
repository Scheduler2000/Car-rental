package database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import model.car.Car;
import model.car.CarBuilder;

public class CarRepository extends DatabaseHandler<Car> {

    public CarRepository(Connection connection) {
        super(connection, "Car");
    }

    @Override
    public boolean Create(Car entity) throws SQLException {
        String sql = "INSERT INTO Car (brand, model, rental_price, description, thumbnail) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setString(1, entity.GetBrand());
        statement.setString(2, entity.GetModel());
        statement.setFloat(3, entity.GetRentalPrice());
        statement.setString(4, entity.GetDescription());
        statement.setString(5, entity.GetThumbnail());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean Update(Car entity) throws SQLException {
        String sql = "UPDATE Car SET rental_price=?, thumbnail=? WHERE id=?";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setFloat(1, entity.GetRentalPrice());
        statement.setString(2, entity.GetThumbnail());
        statement.setInt(3, entity.GetId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public List<Car> GetEntities(Car predicate) throws SQLException {
        var carBuilder = new CarBuilder();
        var cars = new ArrayList<Car>();
        String sql = "";

        if (predicate == null) {
            sql = "SELECT * FROM Car";
        } else {
            var query = new StringBuilder();
            query.append("SELECT * FROM Car WHERE ");

            if (predicate.GetId() != -1)
                query.append("id='" + predicate.GetId() + "' AND ");
            if (predicate.GetBrand() != null)
                query.append("brand='" + predicate.GetBrand() + "' AND ");
            if (predicate.GetModel() != null)
                query.append("model='" + predicate.GetModel() + "' AND ");
            if (predicate.GetRentalPrice() != null)
                query.append("rental_price='" + predicate.GetRentalPrice() + "' AND ");

            query.delete(query.length() - 5, query.length()); /* remove AND */

            sql = query.toString();
        }

        Statement statement = super.connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            carBuilder.SetId(rs.getInt("id")).SetBrand(rs.getString("brand")).SetModel(rs.getString("model"))
                    .SetRentalPrice(rs.getFloat("rental_price")).SetDescription(rs.getString("description"))
                    .SetThumbnail(rs.getString("thumbnail"));

            cars.add(carBuilder.Build());
        }

        return cars;
    }

}
