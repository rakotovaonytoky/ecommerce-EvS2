<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.javainuse.model.Categorie" %>
<%@ page import="java.util.List" %>
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
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <jsp:include page="segment/adminHeader.jsp" />

    <%--   Eto no tapahana de includena    --%> 
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Ajout produits</h1>

                <div class="row">
                    <div class="col-lg-12">
                        <form action="<%= request.getContextPath() %> /admin/adding" method="POST"  enctype="multipart/form-data">
                            <!-- Name input -->
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">Nom du produits:</label>
                                <input type="text" id="form4Example1" class="form-control" name="nom" required/>

                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example3">Description:</label>
                                <textarea class="form-control" id="form4Example3" rows="4" name="description" required></textarea>
                            </div>

                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <label class="form-label" for="inputState">Categorie:</label>
                                <% if(request.getAttribute("categorie")!=null){
                                List<Categorie> listcateg=(List<Categorie>)request.getAttribute("categorie");    
                                %>
                                <select id="inputState" class="form-control" name="idcategorie" required>
                                  <% for(Categorie c : listcateg){%>
                                  <option value="<%=c.getId() %>"><%=c.getNom() %></option>
                                      <% } %>
                                </select>
                                <% } %>
                            </div>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">prix:</label>
                                <input type="text" pattern="[0-9]+" id="form4Example1" class="form-control" name="prix" required/>

                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">Quantite en stock:</label>
                                <input type="text" pattern="[0-9]+" id="form4Example1" class="form-control" name="qte" required/>

                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">Quantite en vente:</label>
                                <input type="text"  id="form4Example1" class="form-control" name="qteenvente" required/>

                            </div>
                             <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">image1:</label>
                                <input type="file" name="file1" />
                            </div>
                             <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">image2:</label>
                                <input type="file" name="file2" />
                            </div>
                             <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">image3:</label>
                                <input type="file" name="file3" />
                            </div>
                             <div class="form-outline mb-4">
                                <label class="form-label" for="form4Example1">image4:</label>
                                <input type="file" name="file4" />
                            </div>

                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-block mb-4">Ajouter</button>
                        </form>
                    </div>

                </div>
            </div>
        </main>
        <%@include file="segment/adminFooter.jsp" %>