//frageEingereicht.js

//Weiterleitung nach erfolgreich eingereichter Frage durch Student
function redirectToMainPage() {
    window.location.href = 'fragenkatalogAnzeigeStudent.xhtml';
}

// Die Funktion nach 3 Sekunden aufrufen
setTimeout(redirectToMainPage, 3000);
