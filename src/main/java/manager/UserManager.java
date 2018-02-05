package manager;

import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private DBConnectionProvider provider;
    private Connection connection;

    public UserManager() {
        provider = DBConnectionProvider.getInstance();
        connection = provider.getConnection();
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO `user`(`name`,`surname`,`age`,`email`,`password`,`profilePic`) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement =
                connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setInt(3, user.getAge());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setString(6,user.getProfilePic());

        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            user.setId(generatedKeys.getLong(1));
        }
    }

    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        User user = null;
        String query = "Select * from `user` where `email` = ? and `password` = ?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setAge(resultSet.getInt(4));
            user.setEmail(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
            user.setProfilePic(resultSet.getString(7));

        }
        return user;
    }
    public User getUserById(long id) throws SQLException {
        User user = null;
        String query = "Select * from `user` where `id` = ?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setAge(resultSet.getInt(4));
            user.setEmail(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
            user.setProfilePic(resultSet.getString(7));

        }
        return user;
    }

    public List<User> getFriendsByUserId(long id) throws SQLException {
        User user = null;
        String query = "Select `friend_id` from `friends` where `user_id` = ?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Long> friendsId = new ArrayList<>();
        while (resultSet.next()) {
            long friendId = resultSet.getLong("friend_id");
            friendsId.add(friendId);
        }
        List <User> friends = new ArrayList<>();
        for (long frId:friendsId) {
            user = getUserById(frId);
            friends.add(user);
        }
        return friends;
    }

    public List<User> getAllUsers() throws SQLException {
        User user = null;
        String query = "Select * from `user`";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setAge(resultSet.getInt(4));
            user.setEmail(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
            user.setProfilePic(resultSet.getString(7));

            users.add(user);
        }
        return users;
    }
    public void addFriend(User user, User friend) throws SQLException {
        String query = "INSERT INTO `friends`(`user_id`,`friend_id`) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setLong(2, friend.getId());
        preparedStatement.executeUpdate();
    }

    public void deleteFriendsByFriendId(long user_id, long friend_id) throws SQLException {

        String query = "DELETE from `friends` where `user_id` = ? AND `friend_id` = ?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(query);
        preparedStatement.setLong(1, user_id);
        preparedStatement.setLong(2, friend_id);
        preparedStatement.executeUpdate();

    }

}

