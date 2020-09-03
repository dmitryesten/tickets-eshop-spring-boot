package com.denisenko.airlineticketsshop.model.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPrimaryKey {

    default long getPrimaryKey(PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.getGeneratedKeys();
        long id = 0;
        if (rs.next())
            id = rs.getInt("ID");

        return id;
    }

}
