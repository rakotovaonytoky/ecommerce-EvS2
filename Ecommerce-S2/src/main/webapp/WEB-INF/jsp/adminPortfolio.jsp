<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.javainuse.model.Portefeuille" %>
<%@ page import="com.javainuse.model.Customer" %>
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
                <h1 class="mt-4">Rechercher produits</h1>
                <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" action="<%= request.getContextPath() %> /admin/searchingProduct" method="GET">
                    <div class="input-group">
                        <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." name="nom" aria-describedby="btnNavbarSearch" />
                        <button class="btn btn-primary" id="btnNavbarSearch" type="submit"><i class="fas fa-search"></i></button>
                    </div>
                </form>
                    <%  String key="listproduit";
                        if(request.getAttribute(key)!=null){
                        List<Portefeuille> listp=(List<Portefeuille>)request.getAttribute(key);
                    %>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col">Portefeuille</th>
                            <th scope="col">Etat</th>
                           
                           
                        </tr>
                    </thead>
                    <tbody>
                        <% if(listp.size()>0){
                        for(Portefeuille p : listp ){    
                        %>
                        <tr>
                            <th scope="row"><%=p.getIdcustomer().getName() %></th>
                            <td><%=p.getMontant() %></td>
                            <td><%=p.getEtat() %></td>
                            <td><a class="btn btn-primary">Valider</a></td>
                            
                            
                        </tr>
                       <% } } %>
                    </tbody>
                </table>
                  <% } %>
                
              

                
            </div>
        </main>
        <%@include file="segment/adminFooter.jsp" %>