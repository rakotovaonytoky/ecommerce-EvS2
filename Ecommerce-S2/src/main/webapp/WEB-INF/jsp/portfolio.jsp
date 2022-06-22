<%@ page import="com.javainuse.model.Produit" %>
<%@ page import="java.util.List" %>
<%--<jsp:include page="segment/header.jsp" />--%>
<%@include file="segment/header.jsp" %>
<!-- Header-->

<!-- Section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
      
        <form name="cart" action="<%= request.getContextPath() %>/customer/ProcessMoney" method="POST">
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
                    <div class="small mb-1"></div>
                    <h1 class="display-5 fw-bolder">Ajout dans Portefeuille</h1>
                    <div class="fs-5 mb-5">
                        <!--<span class="text-decoration-line-through">$45.00</span>-->
                        <span></span>
                    </div>
                    <p class="lead">
                        
                    </p>
                    <div class="d-flex">
                       
                        <input class="form-control text-center" id="inputQuantity" name="money" type="text"  pattern="[0-9]+" value="1" min="10"  />
                        <button class="btn btn-outline-dark flex-shrink-0" type="submit" >
                            <i class="bi-cart-fill me-1"></i>
                            Add to cart
                        </button>
                    </div>
                </div>
            </div>
        </form>
       
    </div>
</section>

<script src="<c:url value="/resources/js/shopping-cart.js" />" ></script>
<%@include file="segment/footer.jsp" %>

