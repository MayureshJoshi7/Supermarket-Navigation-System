(function() {
    var firebaseConfig = {
        apiKey: "AIzaSyBcrvD8Ul5ZwNhLWY55H2BOghuiBc9YDxo",
        authDomain: "supermarket-1b4ce.firebaseapp.com",
        databaseURL: "https://supermarket-1b4ce.firebaseio.com",
        projectId: "supermarket-1b4ce",
        storageBucket: "supermarket-1b4ce.appspot.com",
        messagingSenderId: "888683693363",
        appId: "1:888683693363:web:b48a092490c1fc441b2a46",
        measurementId: "G-J2QCSTCELC"
      }; 
      // Initialize Firebase
      firebase.initializeApp(firebaseConfig);
      const database = firebase.database();

        const pId = document.getElementById('productId');
        const  pName = document.getElementById('productName');
        const pQty = document.getElementById('productQty');
        const pPrice = document.getElementById('productPrice');
        
            // add items to electronics
        btnElectronic.addEventListener('click', (e) => {
            addProduct.addEventListener('click', (e) => {
                database.ref('/electronic/'+ pId.value).set({
                    p_id: pId.value,
                    p_name:pName.value,
                    p_price:pPrice.value,
                    p_qty:pQty.value
                });
                alert(pName.value+" is added inside electronics section");
            });
    
        }); 

}());


function electronic_Function(){
    var x = document.getElementById("txtDisplayCat3");
    var electro = document.getElementById("btnElectronic");
    x.innerHTML = "Electronics selected";
    x.style.color = '#f77f00';
}
