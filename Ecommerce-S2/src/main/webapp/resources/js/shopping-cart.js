

function SaveItem() {
    var id = parseInt(document.forms.cart.id.value);
    var nom = document.forms.cart.nom.value;
    var prix = parseInt(document.forms.cart.prix.value);
    var quantite = parseInt(document.forms.cart.quantite.value);
    var tableau = new Array(4);
    tableau[0] = id;
    tableau[1] = nom;
    tableau[2] = prix;
    tableau[3] = quantite;
    checkProduitInCart(id);
    localStorage.setItem(id, JSON.stringify(tableau));
     alert("Ajout avec success");
    updateCart();
    doShowAll();
}



function doShowAll() {

    var key = "";
    var list = "<thead><tr><th scope='row'>Item</th><th>Nom</th><th>Prix</th><th>Quantite</th></tr></thead>\n";
    var i = 0;
    //for more advance feature, you can set cap on max items in the cart
    var tempStorage = "";
    for (i = 0; i <= localStorage.length - 1; i++) {
        key = localStorage.key(i);
        tempStorage = JSON.parse(localStorage.getItem(key));
        list += "<tr id='tacol" + i + "' >\n\
            <td >\n\
            <input type='hidden' name='id" + i + "' value='" + tempStorage[0] + "' />\n\
                " + tempStorage[0] + "</td>\n\
            <td>" + tempStorage[1] + "</td>\n\
            <td>" + tempStorage[2] + "</td>\n\
            <td> <input type='number' name='quantite" + i + "' value='" + tempStorage[3] + "' onchange='updateQuantity(this," + key + ")'  />  </td>\n\
            <td>" + "<button type='button' class='btn btn-danger' onclick='remove(this," + key + ")'>Effacer</button>" + "</td>\n\
            </tr>\n";
    }
    if (list == "<tr><th scope='row'>Item</th><th>Nom</th><th>Prix</th><th>Quantite</th></tr>\n") {
        list += "<tr><td colspan='3'><i>empty</i></td></tr>\n";
    }
    document.getElementById('list').innerHTML = list;
    shoppingList();
   EnableButtonCart();
//    document.getElementById("validateCart").disabled = false;

}

function remove(tablerow, key) {
    console.log(tablerow);
    console.log(key);
    var row = tablerow.parentNode.parentNode;
    row.parentNode.removeChild(row);
    localStorage.removeItem(key);
    location.reload(true);
    updateCart();
    EnableButtonCart();
    doShowAll();
}

function updateCart() {
    var totalPrice = 0;
    for (i = 0; i <= localStorage.length - 1; i++) {
        key = localStorage.key(i);
        tempStorage = JSON.parse(localStorage.getItem(key));
        totalPrice += tempStorage[2] * tempStorage[3];
    }
    document.getElementById('total').innerHTML = "Total:  $" + totalPrice;
    document.getElementById('totalprixproduit').value = totalPrice;
}
function updateQuantity(quantity, key) {
    quantity = parseInt(quantity.value);
    var item = JSON.parse(localStorage.getItem(key));
    if (quantity <= 0 || isNaN(quantity)) {
        quantity = 1;
    }
    var tableau = new Array(4);
    tableau[0] = item[0];
    tableau[1] = item[1];
    tableau[2] = item[2];
    tableau[3] = quantity;
    localStorage.setItem(tableau[0], JSON.stringify(tableau));
    updateCart();

}
function shoppingList() {
    const number = document.getElementById("total-product");
//    console.log(document.getElementById("total-product").innerHTML);
    var total = localStorage.length;
    if (localStorage.length < 0) {
        total = 0
    }
    ;
    console.log(total);
    number.textContent = total;
}
function checkProduitInCart(id) {
    if(localStorage.length >0){
        for (i = 0; i <= localStorage.length - 1; i++) {
        if (localStorage.key(i) == id) {
            alert("Ce produit est déja dans votre panier");
            return;
        }
       
        }
    }
}
function EnableButtonCart(){
     if( localStorage.length >0){
        document.getElementById("validateCart").disabled = false;
    }
    }