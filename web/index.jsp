<%@ page import="utils.Listener" %>
<%--
  Home Page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>SupPictures</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%
    Listener counter = (Listener) session.getAttribute("counterListenerAccess");

    List<String> listPaths = (List<String>) request.getAttribute("listPictures");
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

<h1 class="titleSupPictures">Welcome to SupPictures</h1>

<div id="stats">
    <p>Currently <%= counter.getNumbersOnline() %> on SupPictures</p>
    <p>We're actually hosting <%= listPaths.size()%> pictures</p>
</div>

<div id="browsePictures">
    <form action="SearchPicture" method="post"><label> Search for some pictures :
        <input type="text" class="form-control" id="nameToSearch" name="nameToSearch">
    </label>

        <button class="btn btn-primary" type="submit">Search</button>
    </form>
</div>

<div id="displayPictures">
    <%
        for (String picturePath : listPaths) {
    %> <a href="${pageContext.request.contextPath}/Picture?path=<%=picturePath%>">
    <img width="100px" height="80px" src="img_uploads/<%=picturePath%>" alt=""></a><%
    }
%>
</div>


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
