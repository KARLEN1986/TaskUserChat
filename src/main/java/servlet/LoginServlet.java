package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.User;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class LoginServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String errMessage = "";

        if (Validator.isEmpty(email)){
            errMessage += "email is empty";
        }
        if (Validator.isEmpty(password)){
            errMessage += "password is empty";
        }
        UserManager userManager =  new UserManager();

        if (errMessage.equals("")){
            try {
                User user = userManager.getUserByEmailAndPassword(email,password);
                if (user!=null){
                    HttpSession session = req.getSession();
                    session.setAttribute("user",user);

                    resp.sendRedirect("/home");


                }
                else {
                    req.getSession().setAttribute("message", "Incorrect Email or password");
                    resp.sendRedirect("/login.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
