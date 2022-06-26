
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
                " + existingEntries[i].id + "</td>\n\
            <td>" + existingEntries[i].nom + "</td>\n\
            <td>" + existingEntries[i].prix  + "</td>\n\
            <td> <input type='number' name='quantite" + i + "' value='" + existingEntries[i].qte + "' onchange='updateQuantity(this," + existingEntries[i].id + ")'  />  </td>\n\
            <td>" + "<button type='button' class='btn btn-danger' onclick='remove(this," + existingEntries[i].id + ")'>Effacer</button>" + "</td>\n\
            </tr>\n";
}
        if (list == "<tr><th scope='row'>Item</th><th>Nom</th><th>Prix</th><th>Quantite</th></tr>\n") {
        list += "<tr><td colspan='3'><i>empty</i></td></tr>\n";
        }
document.getElementById('list').innerHTML = list;
        shoppingList();
        // EnableButtonCart();
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
                     existingEntries.splice(i, 1);
                }
            }
            localStorage.removeItem("produit");
             localStorage.setItem("produit", JSON.stringify(existingEntries));
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
        number.textContent = total;
        }

function EnableButtonCart(){
    var existingEntries = JSON.parse(localStorage.getItem("produit"));
            if (existingEntries == null){
                existingEntries = [];
                document.getElementById("validateCart").disabled = false;
            } else {
                      document.getElementById("validateCart").disabled = true;
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
        var getqteEnvte = document.getElementById("qteEnvte" + i).value;
        var getnom = document.getElementById("nom" + i).value;
        var getreste = document.getElementById("reste" + i).value;
        var nombreIngredients = document.getElementById("nombreIngredients" + i).value;
        var ingredientnecessaire = document.getElementById("ingredientnecessaire" + i).value;
        var pourcentage = parseInt(document.getElementById("pourcentage" + i).value);
        var produit = {
        "id":getid.value,
                "prix":getprix,
                "nom":getnom,
                "qteEnvte":getqteEnvte,
                "reste":getreste,
                "nombreIngredients":nombreIngredients,
                "ingredientnecessaire":ingredientnecessaire,
                "pourcentage":pourcentage,
                "qte":1,
        };
        result.push(produit);
//        console.log(result);
}
return result;
        }

function insertInChart(listIngredient){
//    liste produit vide
        var produitAjouter = JSON.parse(localStorage.getItem("produit"));
        if(produitAjouter==null) produitAjouter=[];
                var produitReste = JSON.parse(localStorage.getItem("resteProduit"));
                 if(produitReste==null) produitReste=[];
                var qteDemandeClient = parseInt(document.getElementById("quantite").value);
        for (let i = 0; i < listIngredient.length; i++) {
                if (produitReste == null) {
                        for (let j = 0; j < listIngredient[i].nombreIngredients; j++){
                        produitAjouter.push(listIngredient[i]);
                        }
                        if( listIngredient[i].reste > 0)  produitReste.push(listIngredient[i]);  
                } else {
                       
                        for (let k = 0; k < produitReste.length; k++){
                                if (listIngredient[i].id == produitReste[k].id) {
                                        checkReste(produitReste[k], listIngredient[i]);  
                                        produitReste.splice(k,1);
                                }
                        }
                         for (let j = 0; j < listIngredient[i].nombreIngredients; j++){
                        produitAjouter.push(listIngredient[i]);
                        }
                        if( listIngredient[i].reste > 0)  produitReste.push(listIngredient[i]); 
                        
                }
              
               
        }
// manao grouBy produitAjouter
  var ProduitAjouter = groupByProduit(produitAjouter);
// manao groupby produitreste
  var reste = groupByReste(produitReste) ;
//insertion localStorage produitAjouter
 localStorage.setItem("produit", JSON.stringify(ProduitAjouter));
//insertion localStorage produitReste
 localStorage.setItem("resteProduit", JSON.stringify(reste));
    }
        
      
function checkReste(reste, produits) {
         
        var calculreste = produits.ingredientnecessaire - reste.reste; 
        console.log("reste.ingredientnecessaire :" + produits.ingredientnecessaire);
        console.log("reste.reste :" + reste.reste);
        console.log("calcul :" + calculreste);
        if (calculreste < produits.qteEnvte) {
                console.log("calcul reste inferrieur");
                var quantite=0;
               // if(produits.nombreIngredients >1){
                     quantite = parseInt(produits.nombreIngredients) - 1;
               // }
               // else if(produits.nombreIngredients  == 1){
                  //   quantite = parseInt(produits.nombreIngredients) ;
               // }
                
                produits.nombreIngredients = quantite;
                reste.reste = (produits.qteEnvte) - calculreste;
                produits.reste = (produits.qteEnvte) - calculreste;
        } else if (calculreste > produits.qteEnvte) {
                console.log("calcul reste superieur");

                var qte = produits.nombreIngredients * produits.qteEnvte;
                reste.reste = qte - calculreste;
                produits.reste =qte - calculreste;

        } else {
                console.log("calcul reste egale");
                var quantite = parseInt(produits.nombreIngredients) - 1;
                 produits.nombreIngredients = quantite;
                reste.reste = 0;
                  produits.reste =0;
                
        }
}
 
function groupByProduit(produit) {
        var groupByProduitAjouter = [];
            produit.reduce(function (res, value) {
                if (!res[value.id]) {
                        res[value.id] = {
                                id: value.id,
                                nom: value.nom,
                                qte: 0,
                                reste: value.reste,
                                nombreIngredients: value.nombreIngredients,
                                ingredientnecessaire:value.ingredientnecessaire,
                                prix: value.prix
                        };
                    groupByProduitAjouter.push(res[value.id])
                }
                res[value.id].qte += value.qte;
                return res;
            }, {});
        return groupByProduitAjouter;
}
function groupByReste(reste) {
          var groupByReste = [];
            reste.reduce(function (res, value) {
                if (!res[value.id]) {
                    res[value.id] = { id: value.id, qte: 0 ,nom:value.nom,reste:value.reste };
                    groupByReste.push(res[value.id])
                }
                // res[value.id].reste += value.reste;
                return res;
            }, {});
        return groupByReste;
}

function SaveItemRelease(){
var nbinput = parseInt(document.getElementById("nbinbputT").value);
        var getInputvalue = getListIngredients(nbinput);
        insertInChart(getInputvalue);
        updateCart();
        doShowAll();
}