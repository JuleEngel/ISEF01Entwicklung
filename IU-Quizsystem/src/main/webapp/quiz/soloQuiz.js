function startQuiz() {
	localStorage.setItem("solved", false);
	localStorage.setItem("correctAnswer", "");
}

// Definition der Funktion außerhalb des DOMContentLoaded-Ereignisses
function onSubmit(correctAnswer) {
    solved=true;
    localStorage.setItem("solved", true);
    localStorage.setItem("correctAnswer", correctAnswer);
}

function nextQuestion() {
	localStorage.setItem("solved", false);
	localStorage.setItem("correctAnswer", "");
}

function changeColor(correctAnswer) {
	var answerButton1 = document.getElementById("answerButton1");
    var answerButton2 = document.getElementById("answerButton2");
    var answerButton3 = document.getElementById("answerButton3");
    var answerButton4 = document.getElementById("answerButton4");
    
    //Setze Farbe der Buttons:
    //Button1
	    if (answerButton1.textContent === correctAnswer) {
			answerButton1.style.color = "white";
			answerButton1.style.backgroundColor = "#64FE2E";
		}
		else {
			answerButton1.style.color = "white";
			answerButton1.style.backgroundColor = "red";
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
}

// Aufruf der Funktion onSubmit() von der Methode in deiner XHTML
document.addEventListener("DOMContentLoaded", function(event) {
    // Aufruf der Funktion innerhalb des Ereignisses
    solved = localStorage.getItem("solved");
    correctAnswer = localStorage.getItem("correctAnswer");
	if (solved) {
		if (correctAnswer != "") {
			changeColor(correctAnswer);	
		}
	}
});