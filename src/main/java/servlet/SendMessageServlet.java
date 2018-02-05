package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;



public class SendMessageServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        HttpSession session = req.getSession();
        long toId = Long.parseLong(req.getParameter("toId"));
        User user = (User) session.getAttribute("user");
        String strMessage = req.getParameter("message");
        User friend = null;

        UserManager manager = new UserManager();

        MessageManager messageManager = new MessageManager();

        Message message = new Message();
        message.setFromId(user.getId());
        message.setToId(toId);
        message.setMessage(strMessage);

        try {
            messageManager.addMessage(message);
            resp.sendRedirect("/home");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
