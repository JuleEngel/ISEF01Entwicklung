//soloQuiz.js


// Funktion beim Starten des Quizzes mit Nutzung des lokalen Speichers des Nutzers
function startQuiz() {
    localStorage.setItem("solved", false); // Setzt den Status "gelöst" auf falsch
    localStorage.setItem("correctAnswer", ""); // Setzt die korrekte Antwort auf leer
}

function refreshPage() {
	window.location.href = 'duoQuiz.xhtml';
}

// Funktion bei Absenden einer Antwort zu dem Quiz. Korrekte Antwort speichern und "solved" aktualisieren.
function onSubmit(correctAnswer) {
    solved = true; // Setzt den Status "gelöst" auf wahr
    localStorage.setItem("solved", true); // Aktualisiert den gelösten Status im lokalen Speicher
    localStorage.setItem("correctAnswer", correctAnswer); // Speichert die korrekte Antwort im lokalen Speicher
    localStorage.setItem("refreshed", false)
}

// Funktion, um zur nächsten Frage zu gehen und den lokalen Speicher zu aktualisieren
function nextQuestion() {
    localStorage.setItem("solved", false); // Setzt den Status "gelöst" auf falsch für die nächste Frage
    localStorage.setItem("correctAnswer", ""); // Setzt die korrekte Antwort auf leer für die nächste Frage
}

// Funktion zum Ändern der Farbe der Antwortbuttons basierend auf der Korrektheit der Antwort
function changeColor(correctAnswer) {
    // Selektiert die Antwortbuttons
    var answerButton1 = document.getElementById("answerButton1");
    var answerButton2 = document.getElementById("answerButton2");
    var answerButton3 = document.getElementById("answerButton3");
    var answerButton4 = document.getElementById("answerButton4");

    // Setzt die Farbe der Buttons basierend auf der Korrektheit der Antwort
    // Hier wird grün für korrekte Antwort und rot für falsche Antwort verwendet
    
    // Button1
    if (answerButton1.textContent === correctAnswer) {
        answerButton1.style.color = "white";
        answerButton1.style.backgroundColor = "#64FE2E"; // Grün
    } else {
        answerButton1.style.color = "white";
        answerButton1.style.backgroundColor = "red"; // Rot
    }
    //Button2
		if (answerButton2.textContent === correctAnswer) {
			answerButton2.style.color = "white";
			answerButton2.style.backgroundColor = "#64FE2E";
		}
		else {
			answerButton2.style.color = "white";
			answerButton2.style.backgroundColor = "red";
		}
	//Button3
		if (answerButton3.textContent === correctAnswer) {
			answerButton3.style.color = "white";
			answerButton3.style.backgroundColor = "#64FE2E";
		}
		else {
			answerButton3.style.color = "white";
			answerButton3.style.backgroundColor = "red";
		}
	//Button4
		if (answerButton4.textContent === correctAnswer) {
			answerButton4.style.color = "white";
			answerButton4.style.backgroundColor = "#64FE2E";
		}
		else {
			answerButton4.style.color = "white";
			answerButton4.style.backgroundColor = "red";
		}
		setTimeout(refreshPage, 5000);
}

function getRandomNumber() {
    return Math.floor(Math.random() * (6 - 4 + 1)) + 4;
}

// Aufruf der Funktion onSubmit() von der Methode in deiner XHTML
document.addEventListener("DOMContentLoaded", function() {
    // Überprüft, ob das Dokument geladen ist, und ruft dann die Funktion auf
    solved = localStorage.getItem("solved"); // Holt den gelösten Status aus dem lokalen Speicher
    correctAnswer = localStorage.getItem("correctAnswer"); // Holt die korrekte Antwort aus dem lokalen Speicher
    if (solved) {
        if (correctAnswer != "") {
            changeColor(correctAnswer); // Ändert die Farbe basierend auf der gespeicherten korrekten Antwort
            var randomNumber = getRandomNumber();
            setTimeout(refreshPage, randomNumber * 1000);
        }
    }
});
