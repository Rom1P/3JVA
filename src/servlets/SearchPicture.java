package servlets;

import entities.Picture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchPicture", urlPatterns = "/SearchPicture")
public class SearchPicture extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameToSearch = request.getParameter("nameToSearch");

        List<String> listPaths = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select picture FROM Picture picture where picture.name LIKE '%" + nameToSearch + "'");
        List<entities.Picture> recentPictures = query.getResultList();

        for (Picture picture : recentPictures) {
            listPaths.add(picture.getPath());
        }
        em.close();
        emf.close();

        request.setAttribute("listPictures", listPaths);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
