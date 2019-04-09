<%@ page import="entities.Picture" %>
<%@ page import="java.util.List" %><%--=

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile - SupPictures</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body>

<%
    String username = (String) request.getAttribute("username");
    String password = (String) request.getAttribute("password");
    String phone = (String) request.getAttribute("phone");
    String lastName = (String) request.getAttribute("lastName");
    String firstName = (String) request.getAttribute("firstName");
    String postalAddress = (String) request.getAttribute("postalAddress");
    String email = (String) request.getAttribute("email");
%>
<h1 id="titleUpdate">SupPictures</h1>

<h2>Upload</h2>

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

<h2>Edit Profile</h2>
<form id="profileForm" action="Profile" method="post">
    <div class="form-group">
        <label for="usernameUpdateInput">Username</label>
        <input type="text" class="form-control" id="usernameUpdateInput" placeholder="<%= username %>"
               name="usernameUpdateInput" disabled>
    </div>
    <div class="form-group">
        <label for="passwordUpdateInput">Password</label>
        <input type="password" class="form-control" id="passwordUpdateInput" placeholder="<%= password %>"
               name="passwordUpdateInput">
    </div>
    <div class="form-group">
        <label for="usernameUpdateInput">Phone Number</label>
        <input type="text" class="form-control" id="phoneNumberUpdateInput" placeholder="<%= phone %>"
               name="phoneNumberUpdateInput">
    </div>
    <div class="form-group">
        <label for="usernameUpdateInput">Last Name</label>
        <input type="text" class="form-control" id="lastNameUpdateInput" placeholder="<%= lastName %>"
               name="lastNameUpdateInput">
    </div>
    <div class="form-group">
        <label for="usernameUpdateInput">First Name</label>
        <input type="text" class="form-control" id="firstNameUpdateInput" placeholder="<%= firstName %>"
               name="firstNameUpdateInput">
    </div>
    <div class="form-group">
        <label for="usernameUpdateInput">Postal Address</label>
        <input type="text" class="form-control" id="postalUpdateInput" placeholder="<%= postalAddress %>"
               name="postalUpdateInput">
    </div>
    <div class="form-group">
        <label for="emailUpdateInput">Email address</label>
        <input type="email" class="form-control" id="emailUpdateInput" placeholder="<%= email %>"
               name="emailUpdateInput">
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
</form>

<% List<Picture> allPicturesUser = (List<Picture>) request.getAttribute("picturesUser");%>
<div class="picturesDiv"><% for (Picture picture : allPicturesUser) {%>
    <p id="nameUser"><%=picture.getName()%>
    </p>
    <a href="${pageContext.request.contextPath}/Picture?path=<%=picture.getPath()%>">
        <img width="100px" height="80px" src="img_uploads/<%=picture.getPath()%>" alt=""></a>
    <form action="RemovePicture" method="post">
        <input style="display: none;" name="id" value="<%=picture.getId()%>">
        <button id="deletePicture" name="adminButton" class="btn btn-primary" type="submit" value="deletePicture">Delete
            Picture
        </button>
    </form>
    <%}%>
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
