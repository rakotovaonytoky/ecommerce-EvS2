<%@ page import="com.javainuse.model.Produit" %>
<%@ page import="com.javainuse.model.Ingredient" %>
<%@ page import="com.javainuse.model.Recette" %>
<%@ page import="java.util.List" %>
<%@include file="segment/header.jsp" %>
<!-- Product section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <% String key="recetteDetails";
           if(request.getAttribute(key)!=null){
         Recette p=(Recette)request.getAttribute(key);   
        %>
        <form >
            <div class="row gx-4 gx-lg-5 align-items-center">
                <div class="col-md-6">
                    <!--<img class="card-img-top mb-5 mb-md-0" src="https://dummyimage.com/600x700/dee2e6/6c757d.jpg" alt="..." />-->
                    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="https://dummyimage.com/600x700/dee2e6/6c757d.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item active">
                                <img src="https://dummyimage.com/600x700/dee2e6/6c757d.jpg" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item active">
                                <img src="https://dummyimage.com/600x700/dee2e6/6c757d.jpg" class="d-block w-100" alt="...">
                            </div>
                           
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="small mb-1">SKU: BST-498</div>
                    <h1 class="display-5 fw-bolder"><%=p.getNom() %></h1>
                    <div class="fs-5 mb-5">
                        <% %>
                    </div>
                    <p class="lead">
                        <%=p.getDescription()%>
                    </p>
                    <div class="d-flex">
                        <% List<Ingredient> listIndredient=p.getIngredientList();
                         if(listIndredient.size()>0){ %>
                         <input type="hidden" id="nbinbputT" value="<%=listIndredient.size()%>"/>
                          <%   for(int i=0;i<listIndredient.size();i++){
                        %>
                        <input type="hidden" id="id<%=i %>" value="<%=listIndredient.get(i).getIdproduit().getId() %>">
                        <input type="hidden" id="prix<%=i %>" value="<%=listIndredient.get(i).getIdproduit().getPrix() %>">
                        <input type="hidden" id="qteEnvte<%=i %>" value="<%=listIndredient.get(i).getIdproduit().getQteenvente() %>">
                        <input type="hidden" id="nom<%=i %>" value="<%=listIndredient.get(i).getIdproduit().getNom() %>">
                        <input type="hidden" id="qteIngredient<%=i %>" value="<%=listIndredient.get(i).getQte() %>">
                        <% }} %>
                        <input class="form-control text-center me-3"  id="quantite" type="number" value="1" style="max-width: 4em" />
<!--                        <button class="btn btn-outline-dark flex-shrink-0" type="button" onclick="SaveItem()">
                            <i class="bi-cart-fill me-1"></i>
                            Add to cart
                        </button>-->
                        <button class="btn btn-outline-dark flex-shrink-0" type="button" onclick="SaveItemRelease()">
                            <i class="bi-cart-fill me-1"></i>
                            Add to cart
                        </button>
                    </div>
                        <p class="lead mt-2">
                            Reste en stock : 
                    </p>
                </div>
            </div>
        </form>
        <% } %>
    </div>
</section>
<!-- Related items section-->
<section class="py-5 bg-light">
    <div class="container px-4 px-lg-5 mt-5">
        <h2 class="fw-bolder mb-4">Related products</h2>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Special Item</h5>
                            <!-- Product reviews-->
                            <div class="d-flex justify-content-center small text-warning mb-2">
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                            </div>
                            <!-- Product price-->
                            <span class="text-muted text-decoration-line-through">$20.00</span>
                            $18.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Sale Item</h5>
                            <!-- Product price-->
                            <span class="text-muted text-decoration-line-through">$50.00</span>
                            $25.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Popular Item</h5>
                            <!-- Product reviews-->
                            <div class="d-flex justify-content-center small text-warning mb-2">
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                            </div>
                            <!-- Product price-->
                            $40.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</section>



<%@include file="segment/footer.jsp" %>