package servlets;

import entities.User;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    private int levelUser;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usernameLoginInput");
        String password = request.getParameter("passwordLoginInput");

        boolean validLogin;
        if (username != null && password != null && username.length() > 0 && password.length() > 0) {
            validLogin = checkLogin(username, password);
        } else {
            validLogin = false;
        }

        if (validLogin) {
            HttpSession session = request.getSession();

            session.setAttribute("username", username);
            session.setAttribute("level", levelUser);

            request.removeAttribute("messageLogin");
            response.sendRedirect(request.getContextPath());
        } else {
            request.setAttribute("messageLogin", "Username or password incorrect");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private boolean checkLogin(String username, String password) {
        User user = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select user FROM User user where user.username = '" + username + "' AND user.password = '" + password + "'");
        try {
            user = (User) query.getSingleResult();

            if (user != null) {
                levelUser = user.getLevel();
            }
        } catch (NoResultException ignored) {

        }
        em.close();
        emf.close();
        return user != null;
    }
}
