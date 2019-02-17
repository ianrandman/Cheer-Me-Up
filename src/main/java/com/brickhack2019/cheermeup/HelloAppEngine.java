package com.brickhack2019.cheermeup;

import com.google.appengine.api.utils.SystemProperty;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.sarxos.webcam.Webcam;

@WebServlet(name = "HelloAppEngine", value = "/hello")
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    Properties properties = System.getProperties();

    response.setContentType("text/plain");
    response.getWriter().println("Fuck this "
        + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));
  }

  public static String getInfo() {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }

  public static String handleWebcam() throws IOException {
    Webcam webcam = Webcam.getDefault();
    webcam.open();
    BufferedImage image = webcam.getImage();
    ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));
    System.out.println("Success");
    return "";
  }

}
