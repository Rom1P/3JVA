package servlets;

import entities.Picture;
import entities.User;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Admin", urlPatterns = "/Admin")
public class Admin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String button = request.getParameter("adminButton");

        System.out.println(button);

        if (button.equals("delete")) {
            EntityManagerFactory entityManagerFactory = null;
            entityManagerFactory = Persistence.createEntityManagerFactory("persistMySql");

            EntityManager em = entityManagerFactory.createEntityManager();
            int userId = Integer.parseInt(request.getParameter("idUser"));

            EntityTransaction t = em.getTransaction();
            try {
                t.begin();

                Query deleteUser = em.createQuery("delete FROM User user where user.id = " + userId + "");

                deleteUser.executeUpdate();
                em.flush();
                t.commit();
            } finally {
                if (t.isActive()) {
                    t.rollback();
                } else {

                }
                em.close();
            }


            entityManagerFactory.close();

        } else if (button.equals("upgrade")) {
            EntityManagerFactory entityManagerFactory = null;
            entityManagerFactory = Persistence.createEntityManagerFactory("persistMySql");

            EntityManager em = entityManagerFactory.createEntityManager();
            EntityTransaction t = em.getTransaction();
            try {
                t.begin();
                User user = em.find(User.class, Integer.valueOf(request.getParameter("idUser")));
                user.setLevel(2);
                em.flush();
                t.commit();
            } finally {
                if (t.isActive()) {
                    t.rollback();
                } else {

                }
                em.close();
            }

            entityManagerFactory.close();

        } else if (button.equals("deletePicture")) {
            EntityManagerFactory entityManagerFactory = null;
            entityManagerFactory = Persistence.createEntityManagerFactory("persistMySql");

            EntityManager em = entityManagerFactory.createEntityManager();
            int pictureId = Integer.parseInt(request.getParameter("idPicture"));

            EntityTransaction t = em.getTransaction();
            try {
                t.begin();

                Query deleteUser = em.createQuery("delete FROM Picture picture where picture.id = " + pictureId + "");

                deleteUser.executeUpdate();
                em.flush();
                t.commit();
            } finally {
                if (t.isActive()) {
                    t.rollback();
                } else {

                }
                em.close();
            }

            entityManagerFactory.close();
        }

        response.sendRedirect("Admin");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("usersList", getAllUsers());
        request.setAttribute("picturesList", getAllPictures());
        this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }

    private List<User> getAllUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select user FROM User user where user.level = 1");
        List<User> allUsers = query.getResultList();

        em.close();
        emf.close();

        return allUsers;
    }

    private List<Picture> getAllPictures() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select picture FROM Picture picture");
        List<Picture> allPictures = query.getResultList();

        em.close();
        emf.close();

        return allPictures;
    }
}
