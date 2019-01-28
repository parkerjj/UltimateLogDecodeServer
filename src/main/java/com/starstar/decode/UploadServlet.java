package com.starstar.decode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.starstar.decode.ECC.XlogFileDecoder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;




/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            return;
        }



        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MAX_MEMORY_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        String uploadFolder = getServletContext().getRealPath("")
                + File.separator + DATA_DIRECTORY;

        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);


        byte[] xlogByte = null;
        byte[] encryptKey = null;

        try {

            // Parse the request
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                String fieldName = item.getFieldName();
                if (!item.isFormField() && fieldName.equals("attachments")) {

                    xlogByte = item.get();
                    if (xlogByte == null || xlogByte.length == 0) {
                        continue;
                    }
                }else if (item.isFormField() && fieldName.equals("seed")){
                    // Found Seed
                    String seed = item.getString();
                    encryptKey= DecryptUtility.getDecodeKey(seed);

                }
            }
        } catch (FileUploadException ex) {
            response.getWriter().write("File Upload Error." + ex.toString());
            throw new ServletException(ex);
        } catch (Exception ex) {
            response.getWriter().write("Unknown Error." + ex.toString());
            ex.printStackTrace();
            throw new ServletException(ex);
        }

        if (xlogByte != null){
            String outputString = null;
            try {
                outputString = XlogFileDecoder.ParseBytes(xlogByte);
            }catch (Exception e){
                response.getWriter().write("XLog unzip error. Please check again."  + e.toString());
            }

            try {
                String decryptStr = DecryptUtility.encryptFile(outputString, encryptKey);
                decryptStr = decryptStr.replace("\n" , "\n</br>");
                response.getWriter().write(decryptStr);
            }catch (Exception excption){
                response.getWriter().write("Seed May Error." + excption.toString());
            }
        }else {
            response.getWriter().write("XLog is empty.");
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
        response.getWriter().close();

    }

}