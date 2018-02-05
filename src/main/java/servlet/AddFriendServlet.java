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



public class AddFriendServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        HttpSession session = req.getSession();
        long friendId = Long.parseLong(req.getParameter("friendId"));
        User user = (User) session.getAttribute("user");
        User friend = null;

        UserManager manager = new UserManager();
        try {
          friend = manager.getUserById(friendId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            manager.addFriend(user,friend);
            req.setAttribute("info","Added");
            req.setAttribute("added",manager.getUserById(friend.getId()));
//            req.getRequestDispatcher("home.jsp").forward(req,resp);
            resp.sendRedirect("/home");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}