package servlet.homework;

import java.sql.*;

public class UserDao {
    private Connection connection;
    private static final String INSERT_INTO_USERS =
            "INSERT INTO users(first_name, last_name, age, email, password) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_FROM_USERS_WHERE_EMAIL =
            "SELECT * FROM users WHERE email=";

    public void insert(String firstName, String lastName, int age, String email, String password) throws SQLException {
        connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS);

        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setInt(3, age);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, password);

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public User getByEmail(String email){
        connection = ConnectionUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();

            String select = String.format("%s\'%s\'", SELECT_FROM_USERS_WHERE_EMAIL, email);
            resultSet = statement.executeQuery(select);

            resultSet.next();
            User user = User.of(resultSet);

            connection.close();
            statement.close();
            resultSet.close();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can`t find user!");
        }
    }
}
