package servlets;

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

@WebServlet(name = "Picture", urlPatterns = "/Picture")
public class Picture extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select picture FROM Picture picture where picture.path='" + path + "'");
        entities.Picture pictureToDetails = (entities.Picture) query.getSingleResult();

        em.close();
        emf.close();

        request.setAttribute("namePicture", pictureToDetails.getName());
        request.setAttribute("descriptionPicture", pictureToDetails.getDescription());
        request.setAttribute("categoryPicture", pictureToDetails.getCategory());
        request.setAttribute("datePicture", pictureToDetails.getDate());
        request.setAttribute("pathPicture", pictureToDetails.getPath());

        this.getServletContext().getRequestDispatcher("/picture.jsp").forward(request, response);
    }
}
