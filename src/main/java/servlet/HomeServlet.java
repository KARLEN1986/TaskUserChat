package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class HomeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        UserManager userManager = new UserManager();
        try {
            List<User> allUsers = userManager.getAllUsers();
            req.setAttribute("users", allUsers);
            req.setAttribute("userFriends",userManager.getFriendsByUserId(user.getId()));


            req.getRequestDispatcher("/home.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("/error.jsp");
        }

    }

}
