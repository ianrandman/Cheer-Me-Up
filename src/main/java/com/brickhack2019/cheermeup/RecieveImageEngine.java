package com.brickhack2019.cheermeup;

import com.google.appengine.api.utils.SystemProperty;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "RecieveImageEngine", value = "/image")
public class RecieveImageEngine extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String imgData = request.getParameter("img-data");
        imgData = imgData.substring(22);

        BufferedImage image = null;
        byte[] imageByte;

        System.out.println(imgData);

        BASE64Decoder decoder = new BASE64Decoder();

        imageByte = decoder.decodeBuffer(imgData);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        System.out.println(image);

        String joy = "";

        try {
            FaceDetectApp faceDetectApp = new FaceDetectApp();
            joy = faceDetectApp.detectFaces(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/?emotion="+joy);

    }
}