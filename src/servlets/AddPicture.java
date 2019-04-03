package servlets;

import entities.Picture;

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

        File uploads = new File("/uploads");

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

        String uploadName = fileToSave.getName();
        String description = request.getParameter("description");
        String date = String.valueOf(new Date());
        String category = request.getParameter("selectCategory");

        Picture picture = new Picture(uploadName, description, date, category);

        response.sendRedirect("index.jsp");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // TODO FIX THAT (REWRITE THIS ONE TRY TO SIMPLIFY AND PUT DIRECTLY IN THE CODE UP THERE)

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
