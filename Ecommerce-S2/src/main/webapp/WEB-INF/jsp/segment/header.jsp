<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico" />" />
        <!-- core theme CSS -->
        <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
        <!-- Bootstrap icons-->
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />-->
        <!-- Core theme CSS (includes Bootstrap)-->
        <!--<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>-->
        <script src="<c:url value="/resources/js/all.js" />" ></script>
        <script src="<c:url value="/resources/js/jquery.min3.6.js" />" ></script>
        <!-- CSS only -->
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">-->
    </head>
    <body onload="doShowAll()">
    <!--<body >-->
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid px-4 px-lg-5">
                <a class="navbar-brand" href="#!">Start Bootstrap</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%= request.getContextPath() %>/customer/shop">Accueil</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">Apropos</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Magasin</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="<%= request.getContextPath() %>/customer/loadMoneyPage">Money virtuel</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" action="<%= request.getContextPath() %> /customer/searchingProduct" method="GET">
                        <div class="input-group">
                            <input class="form-control" type="text" name="nom" id="searchParameter" list="searchParameterDatalist" placeholder="Search for..." autocomplete="off" aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                            <datalist id="searchParameterDatalist">
                                
                            </datalist>

                            <button class="btn btn-secondary" id="btnNavbarSearch" type="submit"><i class="fas fa-search"></i></button>
                        </div>
                    </form>
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal">
                            <i class="fas fa-shopping-cart"></i>
                            Cart
                            <span id="total-product" onload="shoppingList()" class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                        </button>
                    </form>
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                User's 
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="<%= request.getContextPath() %>/logout">logout</a></li>


                            </ul>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title h3 text-muted" id="exampleModalLabel">Votre panier</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="/customer/panier" method="GET">
                        <div class="modal-body">
                            <h3>Shopping List</h3>

                            <input type="hidden" id="qteproduit" name="qteProduit" value="" />
                            <input type="hidden" id="totalprixproduit" name="totalprixproduit" value="" />
                            <script>
                                document.getElementById("qteproduit").value = localStorage.length;
                               
                            </script>
                            <table class="table" id=list></table>
                            <!--<input type=submit value="Valider" >-->


                            <p class="text-muted h3" id="total"> </p>
                            <p class="text-muted h3" id="totalHidden" style="display:none"> </p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" id="validateCart" class="btn btn-primary" disabled="">Valider</button>
                        </div>
                    </form>

                </div>
            </div>
        </div> 
        <script>
            $(document).ready(function () {
                $("#searchParameter").keyup(function () {
                    $(this).css("background-color", "pink");
                    console.log($(this).val());


                    $.ajax({
                        type: "GET",
                        url: "http://localhost:8080/customer/autocomplete?nom="+$(this).val(),
                        cache: false,
                        success: function (data) {
                            $("#searchParameterDatalist").html(data);
                            
                        },
                        error: function (err) {
                            $("#searchParameterDatalist").html("<option></option>");
                        }
                    });




                });
            });
        </script>


