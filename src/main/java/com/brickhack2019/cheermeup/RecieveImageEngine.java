package com.brickhack2019.cheermeup;

import com.google.appengine.api.utils.SystemProperty;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "RecieveImageEngine", value = "/image")
public class RecieveImageEngine extends HttpServlet {
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String imgData = request.getParameter("img-data");
        System.out.println("imgData");

    }
}