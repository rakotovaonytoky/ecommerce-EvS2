<%@ page import="com.javainuse.model.Produit" %>
<%@ page import="com.javainuse.model.Categorie" %>
<%@ page import="com.javainuse.model.Recette" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
        <%--<jsp:include page="segment/header.jsp" />--%>
        <%@include file="segment/header.jsp" %>
         <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Shop in style</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop hompeage template</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <% if(request.getAttribute("Paniermessage")!=null){
                String message=(String)request.getAttribute("Paniermessage");
                if(message.equals("ajout avec succces")){
                %>
                <div class="alert alert-success">
                    <%%>
                </div>
                <% }else{ %>
                <%} }%>
                 <h2 class="text-muted">Produit</h2>
                <ul class="list-group-horizontal">
                        <% String categKey="categories";
                        if(request.getAttribute(categKey)!=null){
                            List<Categorie> listcateg=(List<Categorie>)request.getAttribute(categKey);
                            for(Categorie c : listcateg){
                        %>
                        <li class="list-inline-item">
                            <a class="btn btn-primary" href="<%=request.getContextPath()%>/customer/shop?id=<%=c.getId() %>"><%=c.getNom() %></a>
                        </li>
                        <% }}%>
                    </ul>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    
                    <%
                        String key="listproduit";
               if(request.getAttribute(key)!=null){
               List<Produit> listproduit=(List<Produit>)request.getAttribute(key);
               if(listproduit.size() >0){           
                   for(Produit p : listproduit){
            %>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <!--<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>-->
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="product image" />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder"><%=p.getNom() %></h5>
                            <!-- Product price-->
                            <!--<span class="text-muted text-decoration-line-through">$50.00</span>-->
                            $<%=p.getPrix() %>
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%= request.getContextPath() %>/customer/details?id=<%=p.getId() %>">Add to cart</a></div>
                    </div>
                </div>
            </div>
            <% } }}%>     
                </div>
                <h2 class="text-muted">Recette</h2>
               
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                     <%String keyRecette="recettes";
                if(request.getAttribute(keyRecette)!=null){
                    List<Recette> listrecette=(List<Recette>)request.getAttribute(keyRecette);
                    if(listrecette.size() >0){
                        for( Recette r : listrecette){
                %>
                         <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <!--<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>-->
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="product image" />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder"></h5>
                            <!-- Product price-->
                            <!--<span class="text-muted text-decoration-line-through">$50.00</span>-->
                            
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%= request.getContextPath() %>/customer/recetteDetails?id=1">Add to cart</a></div>
                    </div>
                </div>
            </div>
                    <% }} }%>
                </div>
                
            </div>
        </section>
        <%@include file="segment/footer.jsp" %>
    
