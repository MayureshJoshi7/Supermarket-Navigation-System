(function() {
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
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
  const auth = firebase.auth();

//   get all elements
  const txtEmail = document.getElementById('txtEmail');
  const txtPassword = document.getElementById('txtPassword');
  const btnLogin = document.getElementById('btnLogin');
  const btnSignUp = document.getElementById('btnSignUp');
  const btnLogout = document.getElementById('btnLogout');

// login admin
btnLogin.addEventListener('click', e => {
const email = txtEmail.value;
const pass = txtPassword.value;
const auth = firebase.auth();
const promise = auth.signInWithEmailAndPassword(email,pass);
promise.catch(e => alert(e.message));
alert("Logged In as "+ email);
window.location = 'dashboard.html';
});

// sign up new admin
btnSignUp.addEventListener('click', e => {
    // TODO:
    const email = txtEmail.value;
    const pass = txtPassword.value;
    const auth = firebase.auth();
    const promise = auth.createUserWithEmailAndPassword(email,pass);
    promise
    .catch(e => alert(e.message));

    alert("Signed In");

});

btnSignUp.btnLogout.addEventListener('click',e => {
    firebase.auth().signOut();
    alert("Logged Out");
});


// realtime listner
firebase.auth().onAuthStateChanged(firebaseUser => {
if(firebaseUser) {
    // is signed in
    console.log(firebaseUser);
    btnLogout.classList.remove('hide');
    window.location = 'dashboard.html';
    alert("Active User"+ email);
    
    
} else {
    // no user signed in
    alert("No Active User");
}
});

}());


