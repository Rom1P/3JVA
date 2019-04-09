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
import java.util.List;

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


            EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
            EntityManager em = emf.createEntityManager();

            Query queryPicture = em.createQuery("select user FROM User user where user.username='" + username + "'");
            List<User> allPicturesUser = queryPicture.getResultList();
            em.close();
            emf.close();

            if (allPicturesUser.size() > 0) {
                request.setAttribute("messageRegister", "User with this username already exists");

                this.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            } else {
                boolean success = register(username, password, phone, lastName, firstName, postal, email);

                if (success) {
                    HttpSession session = request.getSession();

                    request.removeAttribute("messageRegister");
                    session.setAttribute("username", username);
                    response.sendRedirect(request.getContextPath());
                } else {
                    request.setAttribute("messageRegister", "Error while contacting the database. Please try again later");

                    this.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                }
            }


        } else {
            request.setAttribute("messageRegister", "Please check validity of your inputs");

            this.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);

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

        if (username == null || password == null || phone == null || lastName == null || firstName == null || postal == null || email == null) {
            validity = false;
        }

        return validity;
    }
}
