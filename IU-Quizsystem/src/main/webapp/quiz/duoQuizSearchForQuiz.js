//duoQuizSearchForQuiz.js

// Generiere eine zufällige Zahl zwischen 3 und 7 (einschließlich)
function getRandomNumber() {
    return Math.floor(Math.random() * (6 - 2 + 1)) + 2;
}

function redirectToFoundPlayerPage() {
    window.location.href = 'duoQuizFoundQuiz.xhtml';
}

//Weiterleitung nach x-Sekunden zur Imitation eines realen Spielers //FOR-DUMMY
setTimeout(redirectToFoundPlayerPage, getRandomNumber() * 1000);