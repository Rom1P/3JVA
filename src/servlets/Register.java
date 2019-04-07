package servlets;

import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usernameRegisterInput");
        String password = request.getParameter("passwordRegisterInput");
        String phone = request.getParameter("phoneNumberRegisterInput");
        String lastName = request.getParameter("lastNameRegisterInput");
        String firstName = request.getParameter("firstNameRegisterInput");
        String postal = request.getParameter("postalRegisterInput");
        String email = request.getParameter("emailRegisterInput");

        boolean inputValidity = checkInputValidity(username, password, phone, lastName, firstName, postal, email);

        if (inputValidity) {
            boolean success = register(username, password, phone, lastName, firstName, postal, email);

            if (success) {
                HttpSession session = request.getSession();

                session.setAttribute("username", username);
                response.sendRedirect("index.jsp");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }

    private boolean register(String username, String password, String phone, String lastName, String firstName, String postal, String email) {

        boolean success = false;

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(phone);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPostalAddress(postal);
        user.setEmail(email);
        user.setLevel(1);


        EntityManagerFactory entityManagerFactory = null;
        entityManagerFactory = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(user);
            em.flush();
            t.commit();
        } finally {
            if (t.isActive()) {
                t.rollback();
            } else {
                success = true;
            }
            em.close();
        }

        return success;
    }

    private boolean checkInputValidity(String username, String password, String phone, String lastName, String firstName, String postal, String email) {
        boolean validity = true;
        return validity;
    }
}
