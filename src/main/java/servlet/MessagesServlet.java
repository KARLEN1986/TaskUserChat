package servlet;

import manager.MessageManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class MessagesServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        MessageManager messageManager = new MessageManager();

        List<Message> messages = null;
        try {
            messages = messageManager.getMessageByUserId(user.getId());
            req.setAttribute("userMessages", messages);

            req.getRequestDispatcher("/messages.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}