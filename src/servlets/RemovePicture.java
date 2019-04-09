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
import java.io.File;
import java.io.IOException;

@WebServlet(name = "RemovePicture", urlPatterns = "/RemovePicture")
public class RemovePicture extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        if (id != 0) {
            String pathImgToDelete = getPathImg(id);
            if (!pathImgToDelete.equals("")) {
                deleteImg(id); //Remove DB

                File file = new File(pathImgToDelete);  //Remove local img
                if (file.delete()) {
                    System.out.println("File deleted successfully !");
                } else {
                    System.out.println("Failed to delete !");
                }
            }
        }

        response.sendRedirect("Profile");
    }

    private String getPathImg(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select picture.path FROM Picture picture where picture.id =" + id);
        String picturePath = (String) query.getSingleResult();
        em.close();
        emf.close();
        return picturePath;
    }

    private void deleteImg(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistMySql");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete FROM Picture picture where picture.id =" + id);
        int rowsDeleted = query.executeUpdate();
        System.out.println("Entities deleted : " + rowsDeleted);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
