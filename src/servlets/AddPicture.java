package servlets;

import entities.Picture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;


@WebServlet(name = "AddPicture", urlPatterns = "/AddPicture")
@MultipartConfig
public class AddPicture extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("fileUploadInput");
        InputStream fileContent = filePart.getInputStream();

        String appPath = request.getServletContext().getRealPath("/img_uploads");
        File uploads = new File(appPath);

        String uploadedFileName = getSubmittedFileName(filePart);
        assert uploadedFileName != null;
        String fileExtension = uploadedFileName.substring(uploadedFileName.length() - 4);

        if (!fileExtension.startsWith(".")) {
            fileExtension = "." + fileExtension;
        }

        if (!uploads.exists()) {
            uploads.mkdir();
        }

        String shortFileName = uploadedFileName.substring(0, uploadedFileName.length() - 4);

        if (shortFileName.length() < 3) {
            shortFileName += "0";
        }

        File fileToSave = File.createTempFile(shortFileName, fileExtension, uploads);

        try (InputStream input = fileContent) {
            Files.copy(input, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String date = String.valueOf(new Date());
        String category = request.getParameter("selectCategory");
        String uploadName = fileToSave.getName();

        response.sendRedirect("index.jsp");

        savePictureDb(name, description, date, category, uploadName);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // TODO FIX THAT (REWRITE THIS ONE TRY TO SIMPLIFY AND PUT DIRECTLY IN THE CODE UP THERE)

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
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
