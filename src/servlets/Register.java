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
            register(username, password, phone, lastName, firstName, postal, email);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void register(String username, String password, String phone, String lastName, String firstName, String postal, String email) {
        EntityManagerFactory entityManagerFactory = null;
        entityManagerFactory = Persistence.createEntityManagerFactory("persistMySql");

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(phone);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPostalAddress(postal);
        user.setEmail(email);
        user.setLevel(1);

        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(user);
            em.flush();
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    private boolean checkInputValidity(String username, String password, String phone, String lastName, String firstName, String postal, String email) {
        boolean validity = true;
        return validity;
    }
}
