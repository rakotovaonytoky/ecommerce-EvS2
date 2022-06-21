<%@ page import="com.javainuse.model.Produit" %>
<%@ page import="java.util.List" %>
<%--<jsp:include page="segment/header.jsp" />--%>
<%@include file="segment/header.jsp" %>
<!-- Header-->

<!-- Section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 mt-2">
        <%  String key="listproduit";
               if(request.getAttribute(key)!=null){
               List<Produit> listproduit=(List<Produit>)request.getAttribute(key);
               if(listproduit.size() >0){
                  Integer pageList=(Integer)request.getAttribute("pageList");
                  Integer totalElements=(int) (long)(request.getAttribute("totalElements"));
                  Integer pagesize=(Integer)request.getAttribute("pagesize");
                  
                  String currentPage="0";
                  if(request.getParameter("pageNo")!=null){
                      currentPage=request.getParameter("pageNo");
//                      if(currentPage.equals("0")) currentPage="1";
                  }
//                  Integer totalElements=Integer.parseInt((String)request.getAttribute("totalElements"));
//                  Integer pagesize=Integer.parseInt((String)request.getAttribute("pagesize"));
//                    
        
        %>
        <div class="col-12">
            <p class="fs-4">
                page <% out.print(Integer.parseInt(currentPage)+1); %>/ <%=pageList %> sur <%=totalElements%>
                résultats pour :
                <span class="text-danger"> "<% out.print(request.getParameter("nom")); %>"</span></p>
        </div>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">


            <% for(Produit p : listproduit){
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
            <% }%> 

            <!--            <div class="col-12">
                           
                        </div>-->



        </div>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <% 
                    if(request.getParameter("pageNo")!=null && Integer.parseInt(request.getParameter("pageNo")) >0 ){
                    %>
                    <li class="page-item"><a class="page-link" href="<%= request.getContextPath() %>/customer/searchingProduct?nom=<%=request.getParameter("nom")%>&pageNo=<%=currentPage%> ">Previous</a></li>
                    <% } %>
                        <% for(int i=0;i<pageList;i++){%>
                    <li class="page-item"><a class="page-link" href="<%= request.getContextPath() %>/customer/searchingProduct?nom=<%=request.getParameter("nom")%>&pageNo=<%=i%> "><%=i+1 %></a></li>
                        <% }%>


                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </nav>
        </div>
        <% } } %> 
    </div>
</section>

<script src="<c:url value="/resources/js/shopping-cart.js" />" ></script>
<%@include file="segment/footer.jsp" %>

