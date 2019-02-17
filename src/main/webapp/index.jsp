<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.brickhack2019.cheermeup.HelloAppEngine" %>
<%@ page import="com.brickhack2019.cheermeup.Main" %>
<html>
<head>
  <%--<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>--%>
  <%--<script src="ws.js"></script>--%>
  <link rel="stylesheet" type="text/css" href="style.css">
  <%--<link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="capture.js"></script>
    <script>
      ctp = <%= com.brickhack2019.cheermeup.Main.getCtpJson() %>;
      cfnp = <%= com.brickhack2019.cheermeup.Main.cfnpJson() %>;
      console.log(ctp);
      console.log(cfnp);
    </script>
  <title>Hello App Engine Standard Java 8</title>
</head>
<body>
  <%= com.brickhack2019.cheermeup.Main.init() %>
  <div class="hide">
    <canvas id="canvas"></canvas>
  </div>
  <div class="webcam">
    <video id="video">Video stream not available.</video>
  </div>
  <div class="nextbutton">
    <a href="google.com"><img src="img/main/next.png" height="120px" width="120px"></a>
    <!--<img src="next.png" height="50%" width="50%">-->
  </div>
  <div class="emotion">
    <img src="img/smile/smile1.png" height="100px" width="100px">
    <!--<img src="next.png" height="50%" width="50%">-->
  </div>
  <div class="main_img">
    <img src="img/temp/5rrlcu.jpg" height="500px" width="500px">
  </div>
</body>
</html>
