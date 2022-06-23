<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.javainuse.model.Categorie" %>
<%@ page import="com.javainuse.model.Recette" %>
<%@ page import="com.javainuse.model.Ingredient" %>
<%@ page import="com.javainuse.model.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Charts - SB Admin</title>
        <!--<link href="css/styles.css" rel="stylesheet" />-->
        <link href="<c:url value="/resources/css/stylesAdmin.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery.min3.6.js" />" ></script>

        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <jsp:include page="segment/adminHeader.jsp" />

    <%--   Eto no tapahana de includena    --%> 
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <% String keyRecette="recette";
                if(request.getAttribute(keyRecette)==null){
                %>
                <h1 class="mt-4">Ajout produits</h1>     
                <div class="row">
                    <div class="col-lg-12">
                        <form action="<%= request.getContextPath() %> /admin/ProcessaddRecette" method="GET"  ">
                            <!-- Name input -->
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">Nom recette:</label>
                                <input type="text" id="form4Example1" class="form-control" name="nom" required/>

                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example3">Description:</label>
                                <textarea class="form-control" id="form4Example3" rows="4" name="description" required></textarea>
                            </div>
                            <p class="small text-muted">Vous pouvez ajouter les ingredients plus tard</p>
                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-block mb-4">Ajouter</button>
                        </form>
                    </div>

                </div>
                <% } else { %>
                <%  Recette c=(Recette)request.getAttribute(keyRecette); %>
     
                <h1 class="mt-4">Ajouter des Ingredients </h1>     
                <div class="row">
                    <div class="col-lg-12">
                        <form action="<%= request.getContextPath() %> /admin/ProcessaddIngredients" method="GET"  ">
                            <input type="hidden" value="<%=c.getId() %>" name="Idrecette">
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">Produit:</label>
                                <input type="text"  class="form-control" id="searchParameter" list="searchParameterDatalist" name="nomProduit" required/>
                                <datalist id="searchParameterDatalist">
                                    <option value="">
                                </datalist>
                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example3">Quantite:</label>
                                <input number class="form-control" id="form4Example3" pattern="[0-9]+"  name="qte" required/>
                            </div>
                            

                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-block mb-4">Ajouter</button>
                        </form>
                    </div>

                </div>
                <% } %>
            </div>
        </main>
        <script>
            $(document).ready(function () {
                $("#searchParameter").keyup(function () {
                    $(this).css("background-color", "pink");
                    console.log($(this).val());


                    $.ajax({
                        type: "GET",
                        url: "http://localhost:8080/admin/autocompleteIngredients?nom=" + $(this).val(),
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
        <%@include file="segment/adminFooter.jsp" %>