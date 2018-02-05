package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class DeleteFriendServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        long friendId = Long.parseLong(req.getParameter("friendId"));
        User user = (User) session.getAttribute("user");
        User friend = null;

        UserManager manager = new UserManager();

        try {
            manager.deleteFriendsByFriendId(user.getId(),friendId);
            resp.sendRedirect("/home");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
