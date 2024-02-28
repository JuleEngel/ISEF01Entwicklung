//duoQuizWaitForPlayers.js

function getRandomNumber() {
    // Generiere eine zufällige Zahl zwischen 3 und 7 (einschließlich)
    return Math.floor(Math.random() * (6 - 2 + 1)) + 2;
}

function redirectToFoundPlayerPage() {
    window.location.href = 'duoQuizFoundQuiz.xhtml';
}

setTimeout(redirectToFoundPlayerPage, getRandomNumber() * 1000);