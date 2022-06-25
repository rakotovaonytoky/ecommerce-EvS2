

//function SaveItem() {
//var id = parseInt(document.forms.cart.id.value);
//        var nom = document.forms.cart.nom.value;
//        var prix = parseInt(document.forms.cart.prix.value);
//        var quantite = parseInt(document.forms.cart.quantite.value);
//        var tableau = new Array(4);
//        tableau[0] = id;
//        tableau[1] = nom;
//        tableau[2] = prix;
//        tableau[3] = quantite;
//        checkProduitInCart(id);
//        localStorage.setItem(id, JSON.stringify(tableau));
//        alert("Ajout avec success");
//        updateCart();
//        doShowAll();
//        }
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
        
        
        var produit = {
        "id":id,
                "prix":prix,
                "nom":nom,
                "qteEnvte":0,
                "qteIngredient":0,
                "qte":quantite,
        };
         var existingEntries = JSON.parse(localStorage.getItem("produit"));
            if (existingEntries == null) existingEntries = [];
            existingEntries.push(produit);
        
        var groupByProduitAjouter = [];
            existingEntries.reduce(function (res, value) {
                if (!res[value.id]) {
                    res[value.id] = { id: value.id, qte: 0 ,nom:value.nom,prix:value.prix};
                    groupByProduitAjouter.push(res[value.id])
                }
                res[value.id].qte += value.qte;
                return res;
            }, {});
            
             localStorage.setItem("produit", JSON.stringify(groupByProduitAjouter));
        alert("Ajout avec success");
        updateCart();
        doShowAll();
        }



//function doShowAll() {
//
//var key = "";
//        var list = "<thead><tr><th scope='row'>Item</th><th>Nom</th><th>Prix</th><th>Quantite</th></tr></thead>\n";
//        var i = 0;
//        //for more advance feature, you can set cap on max items in the cart
//        var tempStorage = "";
//        for (i = 0; i <= localStorage.length - 1; i++) {
//key = localStorage.key(i);
//        tempStorage = JSON.parse(localStorage.getItem(key));
//        list += "<tr id='tacol" + i + "' >\n\
//            <td >\n\
//            <input type='hidden' name='id" + i + "' value='" + tempStorage[0] + "' />\n\
//                " + tempStorage[0] + "</td>\n\
//            <td>" + tempStorage[1] + "</td>\n\
//            <td>" + tempStorage[2] + "</td>\n\
//            <td> <input type='number' name='quantite" + i + "' value='" + tempStorage[3] + "' onchange='updateQuantity(this," + key + ")'  />  </td>\n\
//            <td>" + "<button type='button' class='btn btn-danger' onclick='remove(this," + key + ")'>Effacer</button>" + "</td>\n\
//            </tr>\n";
//}
//if (list == "<tr><th scope='row'>Item</th><th>Nom</th><th>Prix</th><th>Quantite</th></tr>\n") {
//list += "<tr><td colspan='3'><i>empty</i></td></tr>\n";
//}
//document.getElementById('list').innerHTML = list;
//        shoppingList();
//        EnableButtonCart();
////    document.getElementById("validateCart").disabled = false;
//
//        }
function doShowAll() {

var key = "";
        var list = "<thead><tr><th scope='row'>Item</th><th>Nom</th><th>Prix</th><th>Quantite</th></tr></thead>\n";
        var i = 0;
        //for more advance feature, you can set cap on max items in the cart
          var existingEntries = JSON.parse(localStorage.getItem("produit"));
            if (existingEntries == null) existingEntries = [];
        for (i = 0; i <= existingEntries.length - 1; i++) {
        list += "<tr id='tacol" + i + "' >\n\
            <td >\n\
            <input type='hidden' name='id" + i + "' value='" + existingEntries[i].id + "' />\n\
                " + existingEntries[i].nom + "</td>\n\
            <td>" + existingEntries[i].prix + "</td>\n\
            <td>" + existingEntries[i].qte  + "</td>\n\
            <td> <input type='number' name='quantite" + i + "' value='" + existingEntries[i].qte + "' onchange='updateQuantity(this," + existingEntries[i].id + ")'  />  </td>\n\
            <td>" + "<button type='button' class='btn btn-danger' onclick='remove(this," + existingEntries[i].id + ")'>Effacer</button>" + "</td>\n\
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
//    console.log(tablerow);
//    console.log(key);
var row = tablerow.parentNode.parentNode;
        row.parentNode.removeChild(row);
    
       var existingEntries = JSON.parse(localStorage.getItem("produit"));
        if (existingEntries !== null){
            const removed=[];
            for (i = 0; i <= existingEntries.length - 1; i++) {
                if(existingEntries[i].id == key){
                    const removed = existingEntries.splice(key, existingEntries.length);
                }
            }
            localStorage.removeItem("produit");
             localStorage.setItem("produit", JSON.stringify(removed));
         }
        location.reload(true);
        updateCart();
        EnableButtonCart();
        doShowAll();
        }

//function updateCart() {
//var totalPrice = 0;
//        for (i = 0; i <= localStorage.length - 1; i++) {
//key = localStorage.key(i);
//        tempStorage = JSON.parse(localStorage.getItem(key));
//        totalPrice += tempStorage[2] * tempStorage[3];
//}
//document.getElementById('total').innerHTML = "Total:  $" + totalPrice;
//        document.getElementById('totalprixproduit').value = totalPrice;
//        }
function updateCart() {
var totalPrice = 0;
var existingEntries = JSON.parse(localStorage.getItem("produit"));
     if (existingEntries !== null){
          
        for (i = 0; i <= existingEntries.length - 1; i++) {
                totalPrice += existingEntries[i].qte * existingEntries[i].prix;
        }
        document.getElementById('total').innerHTML = "Total:  $" + totalPrice;
                document.getElementById('totalprixproduit').value = totalPrice;
        }
        }
        
        
//function updateQuantity(quantity, key) {
//quantity = parseInt(quantity.value);
//        var item = JSON.parse(localStorage.getItem(key));
//        if (quantity <= 0 || isNaN(quantity)) {
//quantity = 1;
//}
//var tableau = new Array(4);
//        tableau[0] = item[0];
//        tableau[1] = item[1];
//        tableau[2] = item[2];
//        tableau[3] = quantity;
//        localStorage.setItem(tableau[0], JSON.stringify(tableau));
//        updateCart();
//        }
function updateQuantity(quantity, key) {
            quantity = parseInt(quantity.value);
         var existingEntries = JSON.parse(localStorage.getItem("produit"));
            if (existingEntries !== null){
        if (quantity <= 0 || isNaN(quantity)) {
            quantity = 1;
            }
        for(let i=0;i<existingEntries.length;i++){
            if(existingEntries[i].id == key){
                existingEntries[i].qte=quantity;
            }
        }
        localStorage.removeItem("produit");
        localStorage.setItem("produit", JSON.stringify(existingEntries));
     }
     }       
        
//function shoppingList() {
//const number = document.getElementById("total-product");
////    console.log(document.getElementById("total-product").innerHTML);
//        var total = localStorage.length;
//        if (localStorage.length < 0) {
//total = 0
//}
//;
//        number.textContent = total;
//        }
function shoppingList() {
const number = document.getElementById("total-product");
var total=0;
    var existingEntries = JSON.parse(localStorage.getItem("produit"));
            if (existingEntries == null){
                existingEntries = [];
                  total = 0
            }else{
            total=existingEntries.length;
            }
;
//        number.textContent = total;
        }
//function checkProduitInCart(id) {
//if (localStorage.length > 0){
//    for (i = 0; i <= localStorage.length - 1; i++) {
//        if (localStorage.key(i) == id) {
//        alert("Ce produit est déja dans votre panier");
//                return;
//        }
//    }
//    }
//}
function checkProduitInCart(id) {

}

//function EnableButtonCart(){
//    if (localStorage.length > 0){
//    document.getElementById("validateCart").disabled = false;
//    }
//}
function EnableButtonCart(){
    var existingEntries = JSON.parse(localStorage.getItem("produit"));
            if (existingEntries == null){
                existingEntries = [];
                document.getElementById("validateCart").disabled = false;
            }
            
}
//    -------------------------------------function release

function calculIngredientNecessaire(qte, pourcentage){
return ((qte * pourcentage) / 100);
        }

function getListIngredients(nbinput){
var result = [];
        for (let i = 0; i < nbinput; i++) {
var getid = document.getElementById("id" + i);
        var getprix = parseInt(document.getElementById("prix" + i).value);
        var getqteEnvte = parseInt(document.getElementById("qteEnvte" + i).value);
        var getnom = document.getElementById("nom" + i).value;
        var getqteIngredient = parseInt(document.getElementById("qteIngredient" + i).value);
        var produit = {
        "id":getid.value,
                "prix":getprix,
                "nom":getnom,
                "qteEnvte":getqteEnvte,
                "qteIngredient":getqteIngredient,
                "qte":1,
        };
        result.push(produit);
//        console.log(result);
}
return result;
        }
//function insertInChart(listIngredient){
////    liste produit vide
//        var produitAjouter = JSON.parse(localStorage.getItem("produit"));
//        if(produitAjouter==null) produitAjouter=[];
//                var produitReste = JSON.parse(localStorage.getItem("resteProduit"));
//                 if(produitReste==null) produitReste=[];
//                var qteDemandeClient = parseInt(document.getElementById("quantite").value);
//                for (let i = 0; i < listIngredient.length; i++) {
//            var resteEnStock = JSON.parse(localStorage.getItem("resteProduit"));
//                    if (resteEnStock !== null){
//            for (let j = 0; j < resteEnStock.length; j++){
//                if (listIngredient[i].id == resteEnStock[j].id){
//                    var qteIngredients = (calculIngredientNecessaire(listIngredient[i].qteIngredient, listIngredient[i].qteEnvte) * qteDemandeClient) - resteEnStock[j].reste;
//                            checkQuantiteIngredientsAndQteProd(qteIngredients, listIngredient[i].qteEnvte, produitAjouter, produitReste, listIngredient[i]);
//                    }
//                    }
//                }
//                else if (produitReste.length >0){
//                    for(let l=0;l<produitReste.length;l++){
//                        if(listIngredient[i].id == produitReste[l].id){
//                            console.log("mandalove ato ohhh");
//                             var qteIngredients = (calculIngredientNecessaire(listIngredient[i].qteIngredient, listIngredient[i].qteEnvte) * qteDemandeClient) - produitReste[l].reste;
//                        checkQuantiteIngredientsAndQteProd(qteIngredients, listIngredient[i].qteEnvte, produitAjouter, produitReste, listIngredient[i]);
//                        
//                        }    
//                    }
//
//                }
//                else{
//                var qteIngredients = (calculIngredientNecessaire(listIngredient[i].qteIngredient, listIngredient[i].qteEnvte) * qteDemandeClient);
//                        checkQuantiteIngredientsAndQteProd(qteIngredients, listIngredient[i].qteEnvte, produitAjouter, produitReste, listIngredient[i]);
//                }
//        }
//        console.log("------");
//                console.log(produitAjouter);
//                console.log("----Reste--");
//                console.log(produitReste);
//// manao grouBy produitAjouter
//  var groupByProduitAjouter = [];
//            produitAjouter.reduce(function (res, value) {
//                if (!res[value.id]) {
//                    res[value.id] = { id: value.id, qte: 0 ,nom:value.nom,prix:value.prix};
//                    groupByProduitAjouter.push(res[value.id])
//                }
//                res[value.id].qte += value.qte;
//                return res;
//            }, {});
//            console.log(groupByProduitAjouter);
//// manao groupby produitreste
//  var groupByReste = [];
//            produitReste.reduce(function (res, value) {
//                if (!res[value.id]) {
//                    res[value.id] = { id: value.id, reste: 0 };
//                    groupByReste.push(res[value.id])
//                }
//                res[value.id].text += value.text;
//                return res;
//            }, {});
//            console.log(groupByReste);
////insertion localStorage produitAjouter
// localStorage.setItem("produit", JSON.stringify(groupByProduitAjouter));
////insertion localStorage produitReste
// localStorage.setItem("resteProduit", JSON.stringify(groupByReste));
//    }
//        vaovao
function insertInChart(listIngredient){
//    liste produit vide
        var produitAjouter = JSON.parse(localStorage.getItem("produit"));
        if(produitAjouter==null) produitAjouter=[];
                var produitReste = JSON.parse(localStorage.getItem("resteProduit"));
                 if(produitReste==null) produitReste=[];
                var qteDemandeClient = parseInt(document.getElementById("quantite").value);
                for (let i = 0; i < listIngredient.length; i++) {
            var resteEnStock = JSON.parse(localStorage.getItem("resteProduit"));
                    if (resteEnStock !== null){
            for (let j = 0; j < resteEnStock.length; j++){
                if (listIngredient[i].id == resteEnStock[j].id){
                    console.log("misy reste en stock localStorage");
                    var qteIngredients = (calculIngredientNecessaire(listIngredient[i].qteIngredient, listIngredient[i].qteEnvte)  ) - resteEnStock[j].reste;
                            checkQuantiteIngredientsAndQteProd(qteIngredients, listIngredient[i].qteEnvte, produitAjouter, produitReste, listIngredient[i]);
                    }
                    }
                }
                else if (produitReste.length >0){
                    for(let l=0;l<produitReste.length;l++){
                        if(listIngredient[i].id == produitReste[l].id){
                            console.log("mandalove ato ohhh");
                             var qteIngredients = (calculIngredientNecessaire(listIngredient[i].qteIngredient, listIngredient[i].qteEnvte)) - produitReste[l].reste;
                        checkQuantiteIngredientsAndQteProd(qteIngredients, listIngredient[i].qteEnvte, produitAjouter, produitReste, listIngredient[i]);
                        
                        }    
                    }

                }
                else{
                var qteIngredients = (calculIngredientNecessaire(listIngredient[i].qteIngredient, listIngredient[i].qteEnvte));
                        checkQuantiteIngredientsAndQteProd(qteIngredients, listIngredient[i].qteEnvte, produitAjouter, produitReste, listIngredient[i]);
                }
                
        }
        console.log("------");
                console.log(produitAjouter);
                console.log("----Reste--");
                console.log(produitReste);
// manao grouBy produitAjouter
  var groupByProduitAjouter = [];
            produitAjouter.reduce(function (res, value) {
                if (!res[value.id]) {
                    res[value.id] = { id: value.id, qte: 0 ,nom:value.nom,prix:value.prix};
                    groupByProduitAjouter.push(res[value.id])
                }
                res[value.id].qte += value.qte;
                return res;
            }, {});
            console.log(groupByProduitAjouter);
// manao groupby produitreste
  var groupByReste = [];
            produitReste.reduce(function (res, value) {
                if (!res[value.id]) {
                    res[value.id] = { id: value.id, reste: 0 };
                    groupByReste.push(res[value.id])
                }
                res[value.id].text += value.text;
                return res;
            }, {});
            console.log(groupByReste);
//insertion localStorage produitAjouter
 localStorage.setItem("produit", JSON.stringify(groupByProduitAjouter));
//insertion localStorage produitReste
 localStorage.setItem("resteProduit", JSON.stringify(groupByReste));
    }
        
        
        
        
        
        
        
        
        
        
        
function checkQuantiteIngredientsAndQteProd(qteIngredients, qteProd, produitAjouter, produitReste, element){
    var result = qteIngredients - qteProd;
    result =parseInt(result);
    console.log(qteIngredients +"-"+qteProd);
            if (result > 0){
//                let resultDivision = qteIngredients / qteProd;
//                        if (Number.isInteger(resultDivision)){
//                for (let k = 0; k < resultDivision; k++){
                produitAjouter.push(element);
               for(let j=0;j<2;j++){
                    produitAjouter.push(element);
               }

                
                
//                }
//                } else{
//                for (let k = 0; k < parseInt(resultDivision) + 1; k++){
//
//                produitAjouter.push(element);
//                }
                let reste = (resultDivision.toFixed(2)) - parseInt(resultDivision);
                        element = {
                        "id":element.id,
                                "reste":reste
                        };
                        produitReste.push(element);
//                    }
        }else if(result == 0){
             produitAjouter.push(element);
        }else {
        produitAjouter.push(element);
                element = {
                "id":element.id,
                        "reste":(result*-1)
                };
                produitReste.push(element);
        }
}
function isFloat(n){
return Number(n) === n && n % 1 !== 0;
        }
function SaveItemRelease(){
var nbinput = parseInt(document.getElementById("nbinbputT").value);
        var getInputvalue = getListIngredients(nbinput);
        insertInChart(getInputvalue);
        updateCart();
        doShowAll();
 }