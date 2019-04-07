package servlets;

import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
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
            response.sendRedirect("index.jsp");
        } else {
            //TODO returns some errors on login page
            response.sendRedirect("Login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private boolean checkLogin(String username, String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select user FROM User user where user.username = '" + username + "' AND user.password = '" + password + "'");
        User user = (User) query.getSingleResult();
        em.close();
        emf.close();
        return user != null;
    }
}
