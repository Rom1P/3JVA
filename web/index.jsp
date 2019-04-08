<%--
  Home Page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="utils.Listener"%>
<html>
<head>
    <title>SupPictures</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%
    Listener counter = (Listener) session.getAttribute(Listener.COUNTER);
%>

<div id="navbarTop">
    <%
        if (session.getAttribute("username") == null) {
    %>
    <div>
        <div>Want more features ? <a href="${pageContext.request.contextPath}/Login">Login</a> or <a
                href="${pageContext.request.contextPath}/Register">Register</a></div>
    </div>

    <% } else {
        String currentUsername = (String) session.getAttribute("username");
    %>
    <a href="${pageContext.request.contextPath}/Profile"><%= currentUsername %>
    </a>
    <form method="post" action="Logout">
        <button class="btn btn-primary" type="submit">Logout</button>
    </form>

    <%
        }
    %>
</div>

<div id="displayPicture">
    <%
        //TODO LOAD LIST OF ALL PATHS AND FOREACH.....
    %>
</div>

<h1 class="titleSupPictures">Welcome to SupPictures</h1>

<div id="stats">
    <p>Currently <%= counter.getCurrentNbUserSessions() %> on SupPictures</p>
    <p>We're actually hosting . pictures</p>
    <!-- TODO get stats websites -->
</div>

<div id="browsePictures">
    <label> Search for some pictures :
        <input type="text" class="form-control">
    </label>

    <!-- TODO search engine -->
</div>

<%
    if (session.getAttribute("username") != null) {
%>
<form action="AddPicture" method="post" enctype="multipart/form-data">
    <label> Name :
        <input type="text" name="name">
    </label>
    <label> Add a description
        <input type="text" name="description">
    </label>
    <input class="form-control-file" type="file" name="fileUploadInput" accept="image/*"/>
    <label> Associate a category
        <select class="form-control" name="selectCategory">
            <option value="nature">Nature</option>
            <option value="automobile">Automobile</option>
            <option value="animal">Animal</option>
        </select>
    </label>
    <input type="submit"/>
</form>
<% } %>


</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</html>
