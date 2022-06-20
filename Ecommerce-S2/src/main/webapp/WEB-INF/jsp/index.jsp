<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value=""/>

<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Login 08</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="<c:url value="/resources/css/styleLogin.css" />" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">



    </head>
    <body>
        <section class="ftco-section">

            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">Login Admin</h2>
                    </div>

                </div>

                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-5">
                        <c:if test="${param.error != null}">  
                            <div class="alert alert-danger mt-2" role="alert">
                                <p>  
                                    Invalid username and password.  
                                </p> 
                            </div>

                        </c:if>  
                        <c:if test="${param.logout != null}">         
                           <div class="alert alert-success mt-2" role="alert">
                                <p>  
                                   You have been logout successful.
                                </p> 
                            </div>
  
                        </c:if>  
                        <%
                                 if(request.getAttribute("error")!=null){ %>

                        <div class="alert alert-danger mt-2" role="alert">
                            <%=request.getAttribute("error") %>
                        </div>
                        <% } %>
                        <div class="login-wrap p-4 p-md-5">
                            <div class="icon d-flex align-items-center justify-content-center">
                                <span class="fa fa-user-o"></span>
                            </div>
                            <h3 class="text-center mb-4">Haven't an account? 
                                <a href="<%= request.getContextPath() %> /user/signup">SignUp</a>
                            </h3>
                            <form action="/login" method="POST" class="login-form">
                                <div class="form-group">
                                    <input type="text" class="form-control rounded-left" placeholder="Username" name="username" required>
                                </div>
                                <div class="form-group d-flex">
                                    <input type="password" class="form-control rounded-left" placeholder="Password" name="password" required>
                                </div>
                                <div class="form-group d-md-flex">

                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary rounded submit p-3 px-5">Login</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </section>

        <script src="<c:url value="/resources/js/jquery.min.js" />" ></script>
        <script src="<c:url value="/resources/js/popper.js" />" ></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
        <script src="<c:url value="/resources/js/main.js" />" ></script>

    </body>
</html>
<%--<%!--%> 
<!--<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags 
    <meta name="description" content="">
    <meta name="author" content="">
        <link src="<%= request.getContextPath() %>/resources/style.css"  rel="stylesheet">

    <title>Log in with your credentials</title>

    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
    <style>
/*       body{
    background-color: red;
}*/
    </style>

<div class="container">

    <form method="POST" action="/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ">
            <span></span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span></span>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
        </div>

    </form>
    <a href="/register">Register New
                        User</a>

</div>
 /container 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script></body>
</html>-->