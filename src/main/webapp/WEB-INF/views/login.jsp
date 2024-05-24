<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet"  href="../styles/login.css">
</head>
    <body>
        <form class="form-control" action="login" method="post">
            <p class="title">Login</p>
            <div class="input-field">
                <input required="" name="email" id="email" class="input" type="email" />
                <label class="label" for="email">Enter Email</label>
            </div>
            <div class="input-field">
                <input required="" id="password" name="password" class="input" type="password" />
                <label class="label" for="password">Enter Password</label>
            </div>
            <button class="submit-btn">Sign In</button>
            <div class="error-message">
                <p>${loginStatus}</p>
            </div>
        </form>
    </body>
</html>

<style>
    <%@include file="../styles/login.css"%>
</style>