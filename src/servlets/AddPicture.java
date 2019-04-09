package servlets;

import entities.Picture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;


@WebServlet(name = "AddPicture", urlPatterns = "/AddPicture")
@MultipartConfig
public class AddPicture extends HttpServlet {
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();

        Part filePart = request.getPart("fileUploadInput");
        InputStream fileContent = filePart.getInputStream();

        String appPath = request.getServletContext().getRealPath("/img_uploads");
        File uploads = new File(appPath);
        String name = request.getParameter("name");
        File fileToSave = File.createTempFile(name, ".png", uploads);

        try (InputStream input = fileContent) {
            Files.copy(input, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        String description = request.getParameter("description");
        String date = String.valueOf(new Date());
        String category = request.getParameter("selectCategory");
        String uploadName = fileToSave.getName();

        response.sendRedirect(request.getContextPath());

        savePictureDb(name, description, date, category, uploadName);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void savePictureDb(String name, String description, String date, String category, String path) {
        EntityManagerFactory entityManagerFactory = null;
        entityManagerFactory = Persistence.createEntityManagerFactory("persistMySql");

        Picture picture = new Picture();

        picture.setName(name);
        picture.setDescription(description);
        picture.setDate(date);
        picture.setCategory(category);
        picture.setPath(path);
        picture.setUsernamePublisher((String) session.getAttribute("username"));


        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(picture);
            em.flush();
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }
}
