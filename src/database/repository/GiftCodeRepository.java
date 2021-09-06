package database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import model.gift.GiftCode;
import model.gift.GiftCodeBuilder;

public class GiftCodeRepository extends DatabaseHandler<GiftCode> {

    public GiftCodeRepository(Connection connection) {
        super(connection, "gift_code");
    }

    @Override
    public boolean Create(GiftCode entity) throws SQLException {
        String sql = "INSERT INTO gift_code (brand, model, percentage, end_date) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setString(1, entity.GetBrand());
        statement.setString(2, entity.GetModel());
        statement.setFloat(3, entity.GetPercentage());
        statement.setDate(4, new java.sql.Date(entity.GetEndDate().getTime()));

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean Update(GiftCode entity) throws SQLException {
        String sql = "UPDATE gift_code SET percentage=?, end_date=? WHERE id=?";
        PreparedStatement statement = super.connection.prepareStatement(sql);

        statement.setFloat(1, entity.GetPercentage());
        statement.setDate(2, new java.sql.Date(entity.GetEndDate().getTime()));
        statement.setInt(3, entity.GetId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public List<GiftCode> GetEntities(GiftCode predicate) throws SQLException {
        var giftCodeBuilder = new GiftCodeBuilder();
        var giftCodes = new ArrayList<GiftCode>();
        String sql = "";

        if (predicate == null) {
            sql = "SELECT * FROM gift_code";
        } else {
            var query = new StringBuilder();
            query.append("SELECT * FROM gift_code WHERE ");

            if (predicate.GetBrand() != null)
                query.append("brand='" + predicate.GetBrand() + "' AND ");
            if (predicate.GetModel() != null)
                query.append("model='" + predicate.GetModel() + "' AND ");

            query.delete(query.length() - 5, query.length()); /* remove AND */

            sql = query.toString();
        }

        Statement statement = super.connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            giftCodeBuilder.SetId(rs.getInt("id")).SetBrand(rs.getString("brand")).SetModel(rs.getString("model"))
                    .SetPercentage(rs.getFloat("percentage")).SetEndDate(rs.getDate("end_date"));

            giftCodes.add(giftCodeBuilder.Build());
        }

        return giftCodes;
    }

}
