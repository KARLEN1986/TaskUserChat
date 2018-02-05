package manager;

import db.DBConnectionProvider;
import model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private DBConnectionProvider provider;
    private Connection connection;

    public MessageManager() {
        provider = DBConnectionProvider.getInstance();
        connection = provider.getConnection();
    }

    public void addMessage(Message message) throws SQLException {
        String query = "INSERT INTO `message`(`from_id`,`to_id`,`message`) VALUES (?,?,?)";
        PreparedStatement preparedStatement =
                connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, message.getFromId());
        preparedStatement.setLong(2, message.getToId());
        preparedStatement.setString(3, message.getMessage());

        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            message.setId(generatedKeys.getLong(1));
        }
    }

    public List<Message> getMessageByUserId(long userId) throws SQLException {
        Message message = null;
        UserManager userManager = new UserManager();
        String query = "Select * from `message` where `to_id` = ?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(query);
        preparedStatement.setLong(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Message> messages = new ArrayList<Message>();
        while (resultSet.next()) {
            message = new Message();
            message.setId(resultSet.getLong(1));
            message.setFromId(resultSet.getLong(2));
            message.setToId(resultSet.getLong(3));
            message.setMessage(resultSet.getString(4));
            message.setTimestamp(resultSet.getString(5));
            message.setFromUser(userManager.getUserById(message.getFromId()));
            message.setToUser(userManager.getUserById(message.getToId()));

            messages.add(message);
        }
        return messages;
    }

}