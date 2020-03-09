package servlet.homework;

import java.sql.*;
import java.util.Optional;

public class UserDao {
    private Connection connection;
    private static final String INSERT_INTO_USERS =
            "INSERT INTO users(first_name, last_name, age, email, password) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_FROM_USERS_WHERE_EMAIL =
            "SELECT * FROM users WHERE email=?";

    public int insert(String firstName, String lastName, int age, String email, String password) throws SQLException {
        connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement(INSERT_INTO_USERS, Statement.RETURN_GENERATED_KEYS);

        Optional<User> user =  getByEmail(email);

        if (user.isPresent()){
            return 0;
        }

        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setInt(3, age);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, password);

        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        int userId = generatedKeys.getInt(1);

        generatedKeys.close();
        preparedStatement.close();
        connection.close();

        return userId;
    }

    public Optional<User> getByEmail(String email){
        connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            Optional<User> user = Optional.ofNullable(User.of(resultSet));

            connection.close();
            preparedStatement.close();
            resultSet.close();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can`t find user!");
        }
    }
}
