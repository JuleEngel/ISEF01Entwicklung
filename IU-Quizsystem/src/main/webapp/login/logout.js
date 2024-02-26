//logout.js

//Weiterleitung zur login.xhtml
function redirectToLoginPage() {
    window.location.href = 'login.xhtml';
}

// Die Funktion nach 3 Sekunden aufrufen
setTimeout(redirectToLoginPage, 3000);