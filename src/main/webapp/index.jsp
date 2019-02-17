<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.brickhack2019.cheermeup.HelloAppEngine" %>
<%@ page import="com.brickhack2019.cheermeup.RecieveImageEngine" %>
<html>
<head>
  <%--<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>--%>
  <%--<script src="ws.js"></script>--%>
  <link rel="stylesheet" type="text/css" href="style.css">
  <%--<link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="capture.js"></script>
  <title>Hello App Engine Standard Java 8</title>
</head>
<body>

    <%--<div class='wrapper'>--%>
      <%--<h1>webcam websocket transport example</h1>--%>
      <%--<div id="webcams"></div>--%>
    <%--</div>--%>

    <%--<h1>Fuck this -- Java 8! <%= HelloAppEngine.handleWebcam() %></h1>--%>

  <%--<p>This is <%= HelloAppEngine.getInfo() %>.</p>--%>
  <%--<table>--%>
    <%--<tr>--%>
      <%--<td colspan="2" style="font-weight:bold;">Available Servlets:</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
      <%--<td><a href='/hello'>Hello App Engine</a></td>--%>
    <%--</tr>--%>
  <%--</table>--%>

    <video id="video">Video stream not available.</video>
    <div class="hide">
      <canvas id="canvas"></canvas>
    </div>


</body>
</html>
