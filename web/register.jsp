<%--=

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register - SupPictures</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1 id="titleRegister">SupPictures - Sign Up</h1>
<form id="registerForm" action="Register" method="post">
    <div class="form-group">
        <label for="usernameRegisterInput">Username</label>
        <input type="text" class="form-control" id="usernameRegisterInput" placeholder="Enter username"
               name="usernameRegisterInput">
    </div>
    <div class="form-group">
        <label for="passwordRegisterInput">Password</label>
        <input type="password" class="form-control" id="passwordRegisterInput" placeholder="Password"
               name="passwordRegisterInput">
    </div>
    <div class="form-group">
        <label for="usernameRegisterInput">Phone Number</label>
        <input type="text" class="form-control" id="phoneNumberRegisterInput" placeholder="Enter username"
               name="phoneNumberRegisterInput">
    </div>
    <div class="form-group">
        <label for="usernameRegisterInput">Last Name</label>
        <input type="text" class="form-control" id="lastNameRegisterInput" placeholder="Enter username"
               name="lastNameRegisterInput">
    </div>
    <div class="form-group">
        <label for="usernameRegisterInput">First Name</label>
        <input type="text" class="form-control" id="firstNameRegisterInput" placeholder="Enter username"
               name="firstNameRegisterInput">
    </div>
    <div class="form-group">
        <label for="usernameRegisterInput">Postal Address</label>
        <input type="text" class="form-control" id="postalRegisterInput" placeholder="Enter username"
               name="postalRegisterInput">
    </div>
    <div class="form-group">
        <label for="emailRegisterInput">Email address</label>
        <input type="email" class="form-control" id="emailRegisterInput" placeholder="Enter email" name="emailRegisterInput">
    </div>
    <button type="submit" class="btn btn-primary">Register</button>
</form>

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
