package project.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface JDBC {
    Connection connection();

    Statement statement(Connection con) throws SQLException;
}
