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

@WebServlet(name = "Profile", urlPatterns = "/Profile")
public class Profile extends HttpServlet {
    private User currentUser;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = (String) request.getParameter("passwordUpdateInput");
        String newPhoneNumber = (String) request.getParameter("phoneNumberUpdateInput");
        String newLastName = (String) request.getParameter("lastNameUpdateInput");
        String newFirstName = (String) request.getParameter("firstNameUpdateInput");
        String newPostal = (String) request.getParameter("postalUpdateInput");
        String newEmail = (String) request.getParameter("emailUpdateInput");


        EntityManagerFactory entityManagerFactory = null;
        entityManagerFactory = Persistence.createEntityManagerFactory("persistMySql");

        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            User user = em.find(User.class, currentUser.getId());
            if (newPassword != null && newPassword.length() > 1) {
                user.setPassword(newPassword);
            }
            if (newPhoneNumber != null && newPhoneNumber.length() > 1) {
                user.setPhoneNumber(newPhoneNumber);
            }
            if (newLastName != null && newLastName.length() > 1) {
                user.setLastName(newLastName);
            }
            if (newFirstName != null && newFirstName.length() > 1) {
                user.setFirstName(newFirstName);
            }
            if (newPostal != null && newPostal.length() > 1) {
                user.setPostalAddress(newPostal);
            }
            if (newEmail != null && newEmail.length() > 1) {
                user.setEmail(newEmail);
            }
            em.flush();
            t.commit();
        } finally {
            if (t.isActive()) {
                t.rollback();
            } else {

            }
            em.close();
        }

        response.sendRedirect("Profile");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select user FROM User user where user.username = '" + username + "'");
        currentUser = (User) query.getSingleResult();
        em.close();
        emf.close();

        request.setAttribute("username", currentUser.getUsername());
        request.setAttribute("password", currentUser.getPassword());
        request.setAttribute("phone", currentUser.getPhoneNumber());
        request.setAttribute("lastName", currentUser.getLastName());
        request.setAttribute("firstName", currentUser.getFirstName());
        request.setAttribute("postalAddress", currentUser.getPostalAddress());
        request.setAttribute("email", currentUser.getEmail());

        this.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
