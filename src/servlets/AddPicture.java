package servlets;

import entities.Picture;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

        String FileNameMeta = fileToSave.toString();
        try {
            File imgFile = new File( FileNameMeta );
            String convertReadingSize;
            double imgFileSize;

            if (imgFile.isFile()) {
                imgFileSize = imgFile.length(); //Octets

                if (imgFileSize < 1024) {
                    convertReadingSize = String.valueOf(imgFileSize).concat("B");
                } else if (imgFileSize > 1024 && imgFileSize < (1024 * 1024)) {
                    convertReadingSize = String.valueOf(Math.round((imgFileSize / 1024 * 100.0)) / 100.0).concat("KB");
                } else {
                    convertReadingSize = String.valueOf(Math.round((imgFileSize / (1024 * 1204) * 100.0)) / 100.0).concat("MB");
                }
            } else {
                convertReadingSize = "Unknown";
            }
            System.out.println("Size File : " + convertReadingSize);

            BufferedImage buffImgFile = ImageIO.read(imgFile);
            int width = buffImgFile.getWidth();
            int height = buffImgFile.getHeight();
            System.out.println("Image dimensions : " + width + " x " + height);

            Path path = Paths.get( FileNameMeta );
            BasicFileAttributes metaFile;
            metaFile = Files.getFileAttributeView( path, BasicFileAttributeView.class).readAttributes();
            FileTime creationFileTime = metaFile.creationTime();

            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String timeCreation = formatDate.format(creationFileTime.toMillis());

            System.out.println("Creation File Time : " + timeCreation);
        }
        catch (Exception e) {
            e.printStackTrace();
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
